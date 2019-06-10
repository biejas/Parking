package SOA.DAO;

import SOA.models.ParkingMeter;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class ParkingMeterDAO {
    @PersistenceContext(unitName = "parking")
    private EntityManager em;

    public List<ParkingMeter> getAllParkingMeters(){
        TypedQuery<ParkingMeter> query = em.createQuery("SELECT e from ParkingMeter e", ParkingMeter.class);
        return query.getResultList();
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
