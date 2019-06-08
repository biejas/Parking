package SOA.services;

import SOA.DAO.EmployeeDAO;
import SOA.models.Employee;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;


@Singleton
@Startup
@Transactional
public class EmployeeService {
    @EJB
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployee(String username){
        return employeeDAO.getEmployeeByUsername(username)
                .orElseThrow(RuntimeException::new);
    }

    public void changePassword(String username, String newPassword){
        Employee employee = getEmployee(username);
        employee.setPassword(DigestUtils.sha512Hex(newPassword));
        employeeDAO.updateEmployee(employee);
    }

    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }
}
