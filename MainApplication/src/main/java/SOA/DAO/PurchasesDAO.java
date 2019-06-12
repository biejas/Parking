package SOA.DAO;

import SOA.models.Purchases;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class PurchasesDAO {
    @PersistenceContext(unitName = "parking")
    private EntityManager em;

    public List<Purchases> getAllPurchases(){
        TypedQuery<Purchases> query = em.createQuery("SELECT e from Purchases e", Purchases.class);
        return query.getResultList();
    }

    public void addPurchase(Purchases purchase){
        try{
            em.persist(purchase);
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
    }
}
