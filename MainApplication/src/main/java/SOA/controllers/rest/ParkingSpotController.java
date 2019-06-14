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
    public Response getParkingSpot(@PathParam("id") Integer id){
        try{
        ParkingSpotDTO parkingSpotDTO = parkingSpotService.getParkingSpotDTO(id);
        if (parkingSpotDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(parkingSpotDTO).build();
        }}catch(RuntimeException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getParkingSpots(){
        try{
        List<ParkingSpotDTO> parkingSpotDTOS = parkingSpotService.getParkingSpotDTO();
        if (!parkingSpotDTOS.isEmpty()) {
            return Response.ok(parkingSpotDTOS).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }}catch(RuntimeException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/street/{street}")
    @Produces("application/json")
    public Response getParkingSpotForStreet(@PathParam("street") String street) {
        try{
        List<ParkingSpotDTO> parkingSpotDTOS = parkingSpotService.getParkingSpotDTOByStreet(street);
        if (!parkingSpotDTOS.isEmpty()) {
            return Response.ok(parkingSpotDTOS).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }}catch(RuntimeException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}

