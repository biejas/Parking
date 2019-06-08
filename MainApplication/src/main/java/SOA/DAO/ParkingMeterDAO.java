package SOA.DAO;

import SOA.models.ParkingMeter;

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
public class ParkingMeterDAO {
    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private EntityManager em;

    public Optional<List<ParkingMeter>> getAllParkingMeters(){
        try {
            TypedQuery<ParkingMeter> query = em.createQuery("SELECT e from ParkingMeter e", ParkingMeter.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<ParkingMeter> getParkingMeterById(Integer id){
        try {
            TypedQuery<ParkingMeter> query = em.createQuery("SELECT e from ParkingMeter e WHERE e.parkingMeterId=:id", ParkingMeter.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }
}
