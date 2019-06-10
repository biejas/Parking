package SOA.services;

import SOA.DAO.TicketsDAO;
import SOA.models.Tickets;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
@Startup
@Transactional
public class TicketService {
    @EJB
    private TicketsDAO ticketsDAO = new TicketsDAO();

    public void updateTicket(Tickets ticket) {ticketsDAO.updateTicket(ticket);}

    private List<Tickets> getAllTickets() {return ticketsDAO.getAllTickets();}
}
