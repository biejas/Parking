package SOA.controllers;

import SOA.SessionExistsException;
import SOA.models.Employee;
import SOA.services.EmployeeService;
import SOA.services.LoginService;
import SOA.utils.SecurityUtils;
import SOA.InvalidCredentialsException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginController {
    @EJB
    private LoginService loginService;

    private String username;
    private String password;
    private boolean invalidateSessionLink = false;

    public void login() {
        try{
            loginService.login(username, password);
            SecurityUtils.redirect("index.xhtml");
        } catch (InvalidCredentialsException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
        } catch (SessionExistsException e){
            invalidateSessionLink = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
        }
    }

    public void logout(){
        loginService.logout();
        SecurityUtils.redirect("login.xhtml");
    }

    public void invalidateSession(String username){
        loginService.invalidateSession(username);
        invalidateSessionLink = false;
        SecurityUtils.redirect("login.xhtml");
    }

    public boolean isAuthenticated() {return SecurityUtils.getLoggedEmployee().isPresent();}

    public boolean isAdmin() {return SecurityUtils.isAdmin();}

    public boolean isNotEmpty(String str) {return !str.isEmpty();}

    public void addEmployee() {
        Employee employee = new Employee();
        employee.setUsername("ewa");
        employee.setPassword("3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79");
        employee.setAdmin(false);
        employee.setRegion(null);
        EmployeeService employeeService = new EmployeeService();
        employeeService.addEmployee(employee);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInvalidateSessionLink() {
        return invalidateSessionLink;
    }

    public void setInvalidateSessionLink(boolean invalidateSessionLink) {
        this.invalidateSessionLink = invalidateSessionLink;
    }
}
