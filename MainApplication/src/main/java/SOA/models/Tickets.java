package SOA.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketsId;

    private Date startDate;
    private Date endDate;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "parkingMeterId")
    private ParkingMeter parkingMeter;

    public int getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(int ticketsId) {
        this.ticketsId = ticketsId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ParkingMeter getParkingMeter() {
        return parkingMeter;
    }

    public void setParkingMeter(ParkingMeter parkingMeter) {
        this.parkingMeter = parkingMeter;
    }
}
