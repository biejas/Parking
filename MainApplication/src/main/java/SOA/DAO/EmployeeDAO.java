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

    public List<Employee> getAllEmployees(){
        TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e", Employee.class);
        return query.getResultList();
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
        try{
            em.merge(employee);
        }
        catch (Exception e){
            System.err.println("Blad przy edycji rekordu" + e);
        }
    }

    public void addEmployee(Employee employee){
        try{
            em.persist(employee);
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
    }

}
