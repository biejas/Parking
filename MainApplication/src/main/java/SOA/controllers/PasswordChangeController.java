package SOA.controllers;

import SOA.services.EmployeeService;
import SOA.utils.SecurityUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class PasswordChangeController {
    @EJB
    private EmployeeService employeeService;

    private String username = SecurityUtils.getLoggedEmployee().orElseThrow(IllegalStateException::new).getUsername();

    private String newPassword;
    private String confirmedNewPassword;

    public void changePassword(){
        if(!newPassword.equals(confirmedNewPassword))
        {
            FacesContext.getCurrentInstance().addMessage("messagespass", new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga!", "Podane hasła nie są takie same!"));
        } else {
            try{
                employeeService.changePassword(username, newPassword);
                SecurityUtils.redirect("index.html");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("messages1", new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga!", "Nie udało się zmienić hasła!"));

            }
        }
    }

    public boolean isNotEmpty(String str) {return !str.isEmpty();}

    public boolean isAdmin() {return SecurityUtils.isAdmin();}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedNewPassword() {
        return confirmedNewPassword;
    }

    public void setConfirmedNewPassword(String confirmedNewPassword) {
        this.confirmedNewPassword = confirmedNewPassword;
    }
}
