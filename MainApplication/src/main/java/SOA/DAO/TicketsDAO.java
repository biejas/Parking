package SOA.DAO;

import SOA.models.Tickets;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class TicketsDAO {
    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private EntityManager em;

    public Optional<List<Tickets>> getAllTickets(){
        try {
            TypedQuery<Tickets> query = em.createQuery("SELECT e from Tickets e", Tickets.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Tickets> getTicketById(Integer id){
        try {
            TypedQuery<Tickets> query = em.createQuery("SELECT e from Tickets e WHERE e.ticketsId=:id", Tickets.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }
}
