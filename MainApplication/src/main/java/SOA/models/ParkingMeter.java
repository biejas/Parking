package SOA.models;


import javax.persistence.*;
import java.util.Set;

@Entity
public class ParkingMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parkingMeterId;

    @ManyToOne
    @JoinColumn(name = "streetId")
    private Street street;

    @OneToMany(mappedBy = "parkingMeter")
    private Set<Tickets> ticketsSet;

    public Integer getParkingMeterId() {
        return parkingMeterId;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Set<Tickets> getTicketsSet() {
        return ticketsSet;
    }

    public void setTicketsSet(Set<Tickets> ticketsSet) {
        this.ticketsSet = ticketsSet;
    }
}
