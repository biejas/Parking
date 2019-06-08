package SOA.DAO;

import SOA.models.Region;

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
public class RegionDAO {
    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private EntityManager em;

    public Optional<List<Region>> getRegions(){
        try {
            TypedQuery<Region> query = em.createQuery("SELECT e from Region e", Region.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Region> getRegionById(Integer id){
        try {
            TypedQuery<Region> query = em.createQuery("SELECT e from Region e WHERE e.regionId=:id", Region.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public Optional<Region> getRegionByParkingMeterId(Integer id){
        try {
            TypedQuery<Region> query = em.createQuery("SELECT e.region from ParkingMeter e WHERE e.parkingMeterId=:id", Region.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public Optional<Region> getRegionByParkingSpotId(Integer id){
        try {
            TypedQuery<Region> query = em.createQuery("SELECT e.region from ParkingSpot e WHERE e.parkingSpotId=:id", Region.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

}
