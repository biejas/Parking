package SOA.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketsId;

    @NotNull
    private Long endTime;

    @NotNull
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "parkingMeterId")
    private ParkingMeter parkingMeter;

    @OneToOne
    @JoinColumn(name = "parkingSpotId")
    private ParkingSpot parkingSpot;

    public int getTicketsId() {
        return ticketsId;
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

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Tickets() {
    }
}
