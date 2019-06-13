package SOA.controllers;

import SOA.models.ParkingSpot;
import SOA.services.ParkingSpotService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static SOA.utils.ParkingSpotUtils.hasExpiredTicket;
import static SOA.utils.ParkingSpotUtils.hasNoTicket;

@ManagedBean
@SessionScoped
public class DashboardController {
    @EJB
    private ParkingSpotService parkingSpotService;

    public String getTicketTime(long time){
        if(time == 0) {
            return "No ticket";
        } else {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date(time));
        }
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpotService.getParkingSpotsForEmployee();
    }

    private boolean illegalParkingSpotState(ParkingSpot parkingSpot) {
        if(!parkingSpot.isAvailable() && hasNoTicket(parkingSpot) || hasExpiredTicket(parkingSpot)) return true;
        else return false;
    }

    public void informOfIllegalParkingSpotState(ParkingSpot parkingSpot){
        if(illegalParkingSpotState(parkingSpot)) {
            String message = "Miejsce" + parkingSpot.getParkingSpotId() +"na ulicy"+ parkingSpot.getStreet()+" jest zajęte bez biletu!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga!", message));
        }
    }
}
