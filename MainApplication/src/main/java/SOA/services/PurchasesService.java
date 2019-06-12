package SOA.services;

import SOA.DAO.PurchasesDAO;
import SOA.models.Purchases;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;

@Singleton
@Startup
@Transactional
public class PurchasesService {
    @EJB
    private PurchasesDAO purchasesDAO = new PurchasesDAO();

    public void addPurchase(Purchases purchase) {
        purchasesDAO.addPurchase(purchase);
    }
}
