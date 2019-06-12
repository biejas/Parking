package SOA.controllers.rest;

import SOA.DTO.ParkingSpotDTO;
import SOA.DTO.RegionDTO;
import SOA.services.ParkingSpotService;
import SOA.services.RegionService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/region")
public class RegionController {
    @EJB
    private RegionService regionService;
    @EJB
    private ParkingSpotService parkingSpotService;

    @GET
    @Produces("application/json")
    public List<RegionDTO> getRegions(){
        return regionService.getRegionDTO();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getRegion(@PathParam("id") Integer id){
        return Response.ok(regionService.getRegionDTO(id)).build();
    }

    @GET
    @Path("/{id}/parkingspot")
    @Produces("application/json")
    public List<ParkingSpotDTO> getParkingSpotForRegion(@PathParam("id") Integer id){
        return parkingSpotService.getParkingSpotDTOByRegion(id);
    }



}
