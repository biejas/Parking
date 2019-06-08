package SOA.services;

import SOA.SessionExistsException;
import SOA.models.Employee;
import SOA.utils.SecurityUtils;
import org.apache.commons.codec.digest.DigestUtils;
import SOA.InvalidCredentialsException;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Singleton
@Startup
@Transactional
public class LoginService {
    @EJB
    private EmployeeService employeeService;

    private Map<Employee, HttpSession> employeeHttpSessionMap = new HashMap<>();

    public void login(String username, String password) throws InvalidCredentialsException, SessionExistsException {
        Employee employee = employeeService.getEmployee(username);
        if(!DigestUtils.sha512Hex(password).equals(employee.getPassword())){
            throw  new InvalidCredentialsException();
        }
        HttpSession httpSession = SecurityUtils.getSession();
        httpSession.setAttribute("employee", employee);
        if(Objects.nonNull(employeeHttpSessionMap.get(employee))){
            throw new SessionExistsException();
        }
        employeeHttpSessionMap.put(employee, httpSession);
    }

    public void logout() {
        HttpSession httpSession = SecurityUtils.getSession();
        Employee employee = (Employee) httpSession.getAttribute("employee");
        employeeHttpSessionMap.remove(employee);
        httpSession.invalidate();
    }

    public void invalidateSession(String username){
        employeeHttpSessionMap.entrySet()
                .stream()
                .filter(e -> e.getKey().getUsername().equals(username))
                .findFirst()
                .ifPresent(e -> {e.getValue().invalidate(); employeeHttpSessionMap.remove(e.getKey());
                });
    }
}
