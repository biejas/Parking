package SOA.DAO;

import SOA.models.Tickets;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class TicketsDAO {
    @PersistenceContext(unitName = "parking")
    private EntityManager em;

    public List<Tickets> getAllTickets(){
        TypedQuery<Tickets> query = em.createQuery("SELECT e from Tickets e", Tickets.class);
        return query.getResultList();

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

    public void addTicket(Tickets ticket){
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        try{
            em.persist(ticket);
            entr.commit();
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
        em.close();
    }

    public void updateTicket(Tickets ticket){
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        try{
            em.merge(ticket);
            entr.commit();
        }
        catch (Exception e){
            System.err.println("Blad przy edycji rekordu" + e);
        }
        em.close();
    }
}
