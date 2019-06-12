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
    public Response getRegions(){
        List<RegionDTO> regionDTOS= regionService.getRegionDTO();
        if (regionDTOS.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(regionDTOS).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getRegion(@PathParam("id") Integer id){
        RegionDTO regionDTO = regionService.getRegionDTO(id);
        if (regionDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(regionDTO).build();
        }
    }

    @GET
    @Path("/{id}/parkingspot")
    @Produces("application/json")
    public Response getParkingSpotForRegion(@PathParam("id") Integer id){
        List<ParkingSpotDTO> parkingSpotDTOS= parkingSpotService.getParkingSpotDTOByRegion(id);
        if (parkingSpotDTOS.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(parkingSpotDTOS).build();
        }
    }



}
