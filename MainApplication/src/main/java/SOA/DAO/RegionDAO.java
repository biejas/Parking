package SOA.DAO;

import SOA.models.Region;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class RegionDAO {
    @PersistenceContext(unitName = "parking")
    private EntityManager em;

    public List<Region> getRegions(){
        TypedQuery<Region> query = em.createQuery("SELECT e from Region e", Region.class);
        return query.getResultList();

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
