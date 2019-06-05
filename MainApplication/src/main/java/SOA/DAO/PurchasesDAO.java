package SOA.DAO;

import SOA.models.Purchases;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PurchasesDAO {
    private static EntityManagerFactory factory= Persistence.createEntityManagerFactory("parking");

    private static EntityManager em;

    public static Optional<List<Purchases>> getAllPurchases(){
        try {
            TypedQuery<Purchases> query = em.createQuery("SELECT e from Purchases e", Purchases.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
