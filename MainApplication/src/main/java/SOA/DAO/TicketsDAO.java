package SOA.DAO;

import SOA.models.Tickets;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class TicketsDAO {
    private static EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private static EntityManager em;

    public static Optional<List<Tickets>> getAllTickets(){
        try {
            TypedQuery<Tickets> query = em.createQuery("SELECT e from Tickets e", Tickets.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Tickets> getTicketById(Integer id){
        try {
            TypedQuery<Tickets> query = em.createQuery("SELECT e from Tickets e WHERE e.ticketsId=:id", Tickets.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }
}
