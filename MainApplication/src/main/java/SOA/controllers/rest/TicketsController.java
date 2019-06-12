package SOA.controllers.rest;

import SOA.DTO.ParkingSpotDTO;
import SOA.DTO.TicketsDTO;
import SOA.services.ParkingSpotService;
import SOA.services.TicketService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ticket")
public class TicketsController {
    @EJB
    private TicketService ticketService;

    @EJB
    private ParkingSpotService parkingSpotService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTicket(@PathParam("id") Integer id){
        TicketsDTO ticketsDTO= ticketService.getTicketDTO(id);
        if (ticketsDTO==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(ticketsDTO).build();
        }
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getTickets(){
        List<TicketsDTO> ticketsDTOS= ticketService.getTicketDTO();
        if (ticketsDTOS.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(ticketsDTOS).build();
        }
    }

    @GET
    @Path("/{id}/parkingspot")
    @Produces("application/json")
    public Response getParkingSpotForTicket(@PathParam("id") Integer id){
        ParkingSpotDTO parkingSpotDTO =parkingSpotService.getParkingSpotDTOByTicket(id);
        if (parkingSpotDTO==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(parkingSpotDTO).build();
        }
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public Response addTicket(TicketsDTO ticketsDTO){
        ticketService.addTicket(ticketsDTO);
        return Response.status(Response.Status.CREATED).build();
    }
}
