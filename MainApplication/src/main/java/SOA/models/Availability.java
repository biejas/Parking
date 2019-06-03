package SOA.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int availabilityId;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "parkingSpotId")
    private ParkingSpot parkingSpot;

    @OneToOne
    @JoinColumn(name = "ticketsId")
    private Tickets ticket;

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }
}
