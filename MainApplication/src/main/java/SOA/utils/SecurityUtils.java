package SOA.utils;

import SOA.models.Employee;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

public class SecurityUtils {
    private static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    private static HttpSession getSession(HttpServletRequest req) {
        return req.getSession(true);
    }

    public static HttpSession getSession() {
        return getSession(getRequest());
    }

    private static Optional<Employee> getLoggedEmployee(HttpSession httpSession){
        Object employee = httpSession.getAttribute("employee");
        if(Objects.isNull(employee)){
            return Optional.empty();
        } else {
            return Optional.of( (Employee) employee);
        }
    }

    public static Optional<Employee> getLoggedEmployee() {
        return getLoggedEmployee(getSession());
    }

    public static boolean isAdmin(Employee employee) {
        return employee.isAdmin();
    }

    public static boolean isAdmin(HttpServletRequest httpServletRequest) {
        return getLoggedEmployee(getSession(httpServletRequest)).filter(SecurityUtils::isAdmin).isPresent();
    }

    public static boolean isAdmin() {
        return getLoggedEmployee().filter(SecurityUtils::isAdmin).isPresent();
    }

    public static boolean isAuthenticated(HttpServletRequest httpServletRequest){
        return getLoggedEmployee(getSession(httpServletRequest)).isPresent();
    }

    public static void redirect(String page) {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
