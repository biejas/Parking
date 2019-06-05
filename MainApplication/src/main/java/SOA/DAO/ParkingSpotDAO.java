package SOA.DAO;

import SOA.models.ParkingSpot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDAO {
    private static EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private static EntityManager em;

    public static Optional<List<ParkingSpot>> getAllParkingSpots(){
        try {
            TypedQuery<ParkingSpot> query = em.createQuery("SELECT e from ParkingSpot e", ParkingSpot.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
