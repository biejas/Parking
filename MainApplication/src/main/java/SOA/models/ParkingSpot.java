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

    @ManyToOne
    @JoinColumn(name = "streetId")
    private Street street;

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

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }
}
