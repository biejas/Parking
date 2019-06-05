package SOA.DAO;

import SOA.models.Region;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RegionDAO {
    private static EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private static EntityManager em;

    public static Optional<List<Region>> getRegions(){
        try {
            TypedQuery<Region> query = em.createQuery("SELECT e from Region e", Region.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Region> getRegionById(Integer id){
        try {
            TypedQuery<Region> query = em.createQuery("SELECT e from Region e WHERE e.regionId=:id", Region.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }
}
