package SOA.utils;

import SOA.DTO.ParkingSpotDTO;
import SOA.DTO.RegionDTO;
import SOA.DTO.TicketsDTO;
import SOA.models.ParkingMeter;
import SOA.models.ParkingSpot;
import SOA.models.Region;
import SOA.models.Tickets;

import java.util.Objects;

public class DTOUtils {
    public static Tickets changeTicketsDTOToTickets(TicketsDTO ticketsDTO){
        Tickets tickets = new Tickets();
        tickets.setTicketsId(ticketsDTO.getTicketsId());
        tickets.setDuration(ticketsDTO.getDuration());
        tickets.setEndTime(ticketsDTO.getEndTime());

        ParkingMeter parkingMeter=null;
        if(Objects.nonNull(ticketsDTO.getParkingMeterId())){
            parkingMeter = new ParkingMeter();
            parkingMeter.setParkingMeterId(ticketsDTO.getParkingMeterId());
        }
        tickets.setParkingMeter(parkingMeter);

        ParkingSpot parkingSpot=null;
        if(Objects.nonNull(ticketsDTO.getParkingSpotId())){
            parkingSpot = new ParkingSpot();
            parkingSpot.setParkingSpotId(ticketsDTO.getParkingSpotId());
        }
        tickets.setParkingSpot(parkingSpot);

        return tickets;
    }

    public static TicketsDTO changeTicketsToTicketsDTO(Tickets tickets){
        TicketsDTO ticketsDTO = new TicketsDTO();
        ticketsDTO.setTicketsId(tickets.getTicketsId());
        ticketsDTO.setDuration(tickets.getDuration());
        ticketsDTO.setEndTime(tickets.getEndTime());
        ticketsDTO.setParkingMeterId(tickets.getParkingMeter().getParkingMeterId());
        ticketsDTO.setParkingSpotId(tickets.getParkingSpot().getParkingSpotId());

        return ticketsDTO;
    }

    public static RegionDTO changeRegionToRegionDTO(Region region){
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setRegionId(region.getRegionId());
        regionDTO.setName(region.getName());
        return regionDTO;
    }

    public static ParkingSpotDTO changeParkingSpotToPArkingSpotDTO(ParkingSpot parkingSpot){
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO();
        parkingSpotDTO.setParkingSpotId(parkingSpot.getParkingSpotId());
        parkingSpotDTO.setAvailable(parkingSpot.isAvailable());
        return parkingSpotDTO;
    }

}
