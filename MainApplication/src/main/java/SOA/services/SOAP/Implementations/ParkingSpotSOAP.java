package SOA.services.SOAP.Implementations;

import SOA.DTO.ParkingSpotDTO;
import SOA.models.ParkingSpot;
import SOA.models.Purchases;
import SOA.models.Tickets;
import SOA.services.MessageService;
import SOA.services.ParkingSpotService;
import SOA.services.PurchasesService;
import SOA.services.SOAP.Interfaces.ParkingSpotSOAPInterface;
import SOA.services.TicketService;
import SOA.utils.ParkingSpotUtils;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@WebService(endpointInterface = "SOA.services.SOAP.Interfaces.ParkingSpotSOAPInterface")
public class ParkingSpotSOAP implements ParkingSpotSOAPInterface {
    @EJB
    private ParkingSpotService parkingSpotService;

    @EJB
    private TicketService ticketService;

    @EJB
    private PurchasesService purchasesService;

    @EJB
    MessageService messageService;

    @Override
    public void takeSpot(Integer id) {
        changeParkingSpotState(id, false);
        messageService.sendMessage("Miejsce parkingowe numer "+id+" zostało zajęte");
    }

    @Override
    public void leaveSpot(Integer id) {
        changeParkingSpotState(id, true);
        messageService.sendMessage("Miejsce parkingowe numer "+id+" zostało zwolnione");
    }

    @Override
    public List<ParkingSpotDTO> getParkingSpots() {
        return parkingSpotService.getParkingSpotDTO();
    }

    @Override
    public List<ParkingSpotDTO> getParkingSpotsByStreet(String street) {
        return parkingSpotService.getParkingSpotDTOByStreet(street);
    }

    @Override
    public List<ParkingSpotDTO> getParkingSpotsByRegion(Integer id) {
        return parkingSpotService.getParkingSpotDTOByRegion(id);
    }

    @Override
    public long countNotAvailableSpotsAtStreet(String street) {
        return parkingSpotService
                .getParkingSpotDTOByStreet(street)
                .stream()
                .filter(x -> !x.isAvailable())
                .count();
    }

    @Override
    public long countAvailableSpotsAtStreet(String street) {
        return parkingSpotService
                .getParkingSpotDTOByStreet(street)
                .stream()
                .filter(ParkingSpotDTO::isAvailable)
                .count();
    }

    @Transactional
    private void changeParkingSpotState(Integer id, boolean available){
        ParkingSpot parkingSpot = parkingSpotService.getParkingSpot(id);
        if(parkingSpot.isAvailable()==available) return;

        Tickets ticket = parkingSpot.getTicket();
        if(available){
            Purchases purchases = new Purchases();
            purchases.setParkingSpot(parkingSpot);
            purchases.setTickets(ticket);
            purchasesService.addPurchase(purchases);
        }

        parkingSpot.setAvailable(available);
        parkingSpot.setTicket(null);
        parkingSpotService.updateParkingSpot(parkingSpot);

        if(Objects.nonNull(ticket)){
            ticket.setParkingSpot(null);
            ticketService.updateTicket(ticket);
            ticketService.unregisterTicket(ticket);
        }
    }
}
