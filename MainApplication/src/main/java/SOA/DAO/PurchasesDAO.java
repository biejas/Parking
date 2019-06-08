package SOA.DAO;

import SOA.models.Purchases;

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
public class PurchasesDAO {
    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private EntityManager em;

    public Optional<List<Purchases>> getAllPurchases(){
        try {
            TypedQuery<Purchases> query = em.createQuery("SELECT e from Purchases e", Purchases.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
