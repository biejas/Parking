package SOA.DAO;

import SOA.models.Employee;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static Optional<List<Employee>> getAllEmployees(){
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e", Employee.class);
            return Optional.of(query.getResultList());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Employee> getEmployeeByUsername(String username){
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e from Employee e WHERE e.username=:username", Employee.class)
                    .setParameter("username", username);
            return Optional.of(query.getSingleResult());
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public static void addEmployee(){
        factory=Persistence.createEntityManagerFactory("parking");
        em=factory.createEntityManager();
        EntityTransaction entr=em.getTransaction();
        entr.begin();
        try{
            Employee employee = new Employee();
            employee.setAdmin(false);
            employee.setUsername("Aga");
            employee.setPassword("asas");
            em.persist(employee);
            entr.commit();
        }
        catch (Exception e){
            System.err.println("Blad przy dodawaniu rekordu" + e);
        }
    }

}
