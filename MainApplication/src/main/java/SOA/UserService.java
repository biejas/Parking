package SOA;

import SOA.DAO.EmployeeDAO;
import SOA.models.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class UserService {

    public void addEmployee() {
        EmployeeDAO.addEmployee(new Employee());
    }
}
