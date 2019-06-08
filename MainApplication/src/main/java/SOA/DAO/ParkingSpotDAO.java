package SOA.DAO;

import SOA.models.ParkingSpot;

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
public class ParkingSpotDAO {
    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private EntityManager em;

    public Optional<List<ParkingSpot>> getAllParkingSpots(){
        try {
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e from ParkingSpot e", ParkingSpot.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
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

    public Optional<List<ParkingSpot>> getParkingSpotByRegionId(Integer id){
        try {
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e.parkingSpotSet from Region e WHERE e.regionId=:id", ParkingSpot.class)
                    .setParameter("id", id);
            return Optional.of(query.getResultList());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public Optional<List<ParkingSpot>> getParkingSpotByStreet(String street){
        try {
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e from ParkingSpot e WHERE e.street=:street", ParkingSpot.class)
                    .setParameter("street", street);
            return Optional.of(query.getResultList());
        } catch(Exception e){
            return Optional.empty();
        }
    }
}
