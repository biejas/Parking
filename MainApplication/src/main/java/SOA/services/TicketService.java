package SOA.services;

import SOA.DAO.RegionDAO;
import SOA.DAO.TicketsDAO;
import SOA.DTO.TicketsDTO;
import SOA.models.ParkingSpot;
import SOA.models.Tickets;
import SOA.utils.DTOUtils;
import SOA.utils.ParkingSpotUtils;
import SOA.utils.TicketUtils;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Singleton
@Startup
@Transactional
public class TicketService {
    @EJB
    private TicketsDAO ticketsDAO;

    @EJB
    private MessageService messageService;

    @EJB
    private RegionDAO regionDAO;

    @EJB
    private ParkingSpotService parkingSpotService;

    private Tickets ticketToExpire;
    private ScheduledFuture<?> scheduledFuture;

    public void updateTicket(Tickets ticket) {ticketsDAO.updateTicket(ticket);}

    private List<Tickets> getAllTickets() {return ticketsDAO.getAllTickets();}

    private void addTicket(Tickets ticket){
        ticketsDAO.addTicket(ticket);
        addTicketToParkingSpot(ticket.getTicketsId());
        registerTicket(ticket);
    }

    public Optional<Tickets> getTicketWhichExpiresFirst(){
        return getAllTickets()
                .stream()
                .filter(TicketUtils::isNotExpired)
                .sorted(Comparator.comparing(Tickets::getEndTime))
                .findFirst();
    }

    private void addTicketToParkingSpot(Integer ticketId){
        Tickets ticket = ticketsDAO.getTicketById(ticketId).orElseThrow(RuntimeException::new);
        Set<ParkingSpot> parkingSpotSet = findEmptyParkingSpots(ticket);
        findIllegallyOccupiedParkingSpot(parkingSpotSet).ifPresent(parkingSpot -> {
            ticket.setParkingSpot(parkingSpot);
            updateTicket(ticket);
            parkingSpot.setTicket(ticket);
            parkingSpotService.updateParkingSpot(parkingSpot);
            messageService.sendMessage("Bilet numer " +ticketId+ " został zakupiony dla miejsca parkingowego numer " + parkingSpot.getParkingSpotId());
        });
    }

    private Optional<ParkingSpot> findIllegallyOccupiedParkingSpot(Set<ParkingSpot> parkingSpots) {
        return parkingSpots
                .stream()
                .filter(ParkingSpotUtils::isNotAvailable)
                .filter(ParkingSpotUtils::hasNoTicket)
                .findAny();
    }

    private Set<ParkingSpot> findEmptyParkingSpots(Tickets ticket){
        return regionDAO
                .getRegionByParkingMeterId(ticket.getParkingMeter().getParkingMeterId())
                .orElseThrow(RuntimeException::new)
                .getParkingSpotSet();
    }

    public void registerTicket(Tickets ticket){
        if(Objects.isNull(ticketToExpire) || expiredFaster(ticket)) {
            ticketToExpire = ticket;
            CompletableFuture
                    .runAsync(() -> {
                        try {
                            scheduledFuture = Executors
                                    .newSingleThreadScheduledExecutor()
                                    .schedule(
                                            () -> {messageService.sendMessage("Bilet z miejsca numer " + ticketToExpire.getParkingSpot().getParkingSpotId() + " jest już nieważny.");
                                            unregisterTicketFromParkingSpot(ticketToExpire);},
                                            ticketToExpire.getEndTime() - System.currentTimeMillis(),
                                            TimeUnit.MILLISECONDS
                                    );

                            scheduledFuture.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .thenRun(() -> {
                        ticketToExpire = null;
                        getTicketWhichExpiresFirst().ifPresent(this::registerTicket);
                    });
        }
    }

    public void unregisterTicket(Tickets ticket) {
        if (Objects.nonNull(ticketToExpire) && ticket.getTicketsId()==ticketToExpire.getTicketsId()) {
            scheduledFuture.cancel(true);
            ticketToExpire = null;
        }
    }

    public void unregisterTicketFromParkingSpot(Tickets ticket){
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.setTicket(null);
        parkingSpotService.updateParkingSpot(parkingSpot);
        ticket.setParkingSpot(null);
        ticketsDAO.updateTicket(ticket);
    }

    private boolean expiredFaster(Tickets ticket) {
        return ticket.getEndTime() < ticketToExpire.getEndTime();
    }

    public TicketsDTO getTicketDTO(Integer id){
        return ticketsDAO.getTicketById(id)
                .map(DTOUtils::changeTicketsToTicketsDTO)
                .orElseThrow(RuntimeException::new);
    }

    public List<TicketsDTO> getTicketDTO(){
        return ticketsDAO.getAllTickets()
                .stream()
                .map(DTOUtils::changeTicketsToTicketsDTO)
                .collect(Collectors.toList());
    }

    public void addTicket(TicketsDTO ticketsDTO){
        addTicket(DTOUtils.changeTicketsDTOToTickets(ticketsDTO));
    }



}
