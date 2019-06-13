package SOA.controllers;

import SOA.models.ParkingSpot;
import SOA.services.MessageService;
import SOA.services.ParkingSpotService;

import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static SOA.utils.ParkingSpotUtils.hasExpiredTicket;
import static SOA.utils.ParkingSpotUtils.hasNoTicket;

@ManagedBean
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class DashboardController {
    @EJB
    private ParkingSpotService parkingSpotService;

    @EJB
    MessageService messageService;


    @Inject
    @Push
    PushContext channel;


    public void onMessage(Message message) {
        ObjectMessage msg = (ObjectMessage) message;
        try {
            Object obj = msg.getObject();
            pushMessage("New msg " + msg.toString());


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pushMessage(String message) {
        channel.send(message);
    }

    public String getTicketTime(long time){
        if(time == 0) {
            return "No ticket";
        } else {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date(time));
        }
    }

    public List<ParkingSpot> getParkingSpots() {
        //messageService.getMessage();
        return parkingSpotService.getParkingSpotsForEmployee();
    }

    private boolean illegalParkingSpotState(ParkingSpot parkingSpot) {
        return !parkingSpot.isAvailable() && hasNoTicket(parkingSpot) || hasExpiredTicket(parkingSpot);
    }

    public void informOfIllegalParkingSpotState(ParkingSpot parkingSpot){
        if(illegalParkingSpotState(parkingSpot)) {
            String message = "Miejsce" + parkingSpot.getParkingSpotId() +"na ulicy"+ parkingSpot.getStreet()+" jest zajęte bez biletu!";
            FacesContext.getCurrentInstance().addMessage("messages1", new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga!", message));
        }
    }
}
