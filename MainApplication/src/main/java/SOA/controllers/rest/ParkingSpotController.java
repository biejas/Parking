package SOA.controllers.rest;

import SOA.DTO.ParkingSpotDTO;
import SOA.services.ParkingSpotService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/parkingspot")
public class ParkingSpotController {
    @EJB
    private ParkingSpotService parkingSpotService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPArkingSpot(@PathParam("id") Integer id){
        return Response.ok(parkingSpotService.getParkingSpotDTO(id)).build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<ParkingSpotDTO> getPArkingSpots(){
        return parkingSpotService.getParkingSpotDTO();
    }

    @GET
    @Path("/{street}")
    @Produces("application/json")
    public List<ParkingSpotDTO> getPArkingSpotForStreet(@PathParam("street") String street){
        return parkingSpotService.getParkingSpotDTOByStreet(street);
    }


}

