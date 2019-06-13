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
import java.util.Iterator;
import java.util.List;

import static SOA.utils.ParkingSpotUtils.hasExpiredTicket;
import static SOA.utils.ParkingSpotUtils.hasNoTicket;

@ManagedBean
@SessionScoped
public class DashboardController {
    @EJB
    private ParkingSpotService parkingSpotService = new ParkingSpotService();

    public String getTicketTime(long time){
        if(time == 0) {
            return "No ticket";
        } else {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date(time));
        }
    }

    public List<ParkingSpot> getParkingSpots() {
        clearMessages();
        List<ParkingSpot> parkingSpots= parkingSpotService.getParkingSpotsForEmployee();
        for (ParkingSpot spot: parkingSpots) {
            informOfIllegalParkingSpotState(spot);
        }
        return parkingSpots;
    }

    private boolean illegalParkingSpotState(ParkingSpot parkingSpot) {
        return !parkingSpot.isAvailable() && (hasNoTicket(parkingSpot) || hasExpiredTicket(parkingSpot));
    }

    public void informOfIllegalParkingSpotState(ParkingSpot parkingSpot){
        if(illegalParkingSpotState(parkingSpot)) {
            String message = "Miejsce " + parkingSpot.getParkingSpotId() +" na ulicy "+ parkingSpot.getStreet()+" jest zajęte bez biletu!";
            FacesContext.getCurrentInstance().addMessage("messages1", new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga!", message));
        }
    }

    public void clearMessages(){
        FacesContext context = FacesContext.getCurrentInstance();
        Iterator<FacesMessage> it = context.getMessages();
        while ( it.hasNext() ) {
            it.next();
            it.remove();
        }
    }
}
