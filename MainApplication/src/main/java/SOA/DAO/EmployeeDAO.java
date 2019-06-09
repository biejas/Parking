package SOA.DAO;

import SOA.models.Employee;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class EmployeeDAO {

    @PersistenceContext(unitName = "parking")
    private EntityManager em;

    public Optional<List<Employee>> getAllEmployees(){
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e", Employee.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Employee> getEmployeeByUsername(String username){
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e WHERE e.username=:username", Employee.class)
                    .setParameter("username", username);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public void updateEmployee(Employee employee){
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        try{
            em.merge(employee);
            entr.commit();
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
        em.close();
    }

    public void addEmployee(Employee employee){
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        try{
            em.persist(employee);
            entr.commit();
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
        em.close();
    }

}
