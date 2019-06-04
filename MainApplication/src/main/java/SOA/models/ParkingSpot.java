package SOA.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parkingSpotId;

    @NotNull
    private boolean available;

    @NotNull
    private String street;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToOne
    @JoinColumn(name = "parkingSpotId")
    private Tickets ticket;

    public Integer getParkingSpotId() {
        return parkingSpotId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
