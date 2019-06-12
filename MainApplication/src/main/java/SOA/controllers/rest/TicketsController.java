package SOA.controllers.rest;

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
        return Response.ok(ticketService.getTicketDTO(id)).build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<TicketsDTO> getTickets(){
        return ticketService.getTicketDTO();
    }

    @GET
    @Path("/{id}/parkingspot")
    @Produces("application/json")
    public Response getPArkingSpotForTicket(@PathParam("id") Integer id){
        return Response.ok(parkingSpotService.getParkingSpotDTOByTicket(id)).build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public Response addTicket(TicketsDTO ticketsDTO){
        ticketService.addTicket(ticketsDTO);
        return Response.status(Response.Status.CREATED).build();
    }
}
