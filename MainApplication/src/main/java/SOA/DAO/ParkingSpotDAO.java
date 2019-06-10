package SOA.DAO;

import SOA.models.ParkingSpot;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class ParkingSpotDAO {
    @PersistenceContext(unitName = "parking")
    private EntityManager em;

    public List<ParkingSpot> getAllParkingSpots(){
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e from ParkingSpot e", ParkingSpot.class);
            return query.getResultList();
    }

    public Optional<ParkingSpot> getParkingSpotById(Integer id){
        try {
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e from ParkingSpot e WHERE e.parkingSpotId=:id", ParkingSpot.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public Optional<ParkingSpot> getParkingSpotByTicketId(Integer id){
        try {
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e.parkingSpot from Tickets e WHERE e.ticketsId=:id", ParkingSpot.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public List<ParkingSpot> getParkingSpotByRegionId(Integer id){
        TypedQuery<ParkingSpot> query = em.createQuery("SELECT e.parkingSpotSet from Region e WHERE e.regionId=:id", ParkingSpot.class)
                    .setParameter("id", id);
        return query.getResultList();
    }

    public List<ParkingSpot> getParkingSpotByStreet(String street){
        TypedQuery<ParkingSpot> query = em.createQuery("SELECT e from ParkingSpot e WHERE e.street=:street", ParkingSpot.class)
                    .setParameter("street", street);
        return query.getResultList();

    }

    public void updateParkingSpot(ParkingSpot parkingSpot){
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        try{
            em.merge(parkingSpot);
            entr.commit();
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
        em.close();
    }
}
