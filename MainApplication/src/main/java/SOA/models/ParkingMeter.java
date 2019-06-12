package SOA.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class ParkingMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parkingMeterId;

    @NotNull
    private String street;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToMany(mappedBy = "parkingMeter")
    private Set<Tickets> ticketsSet;

    public void setParkingMeterId(Integer parkingMeterId) {
        this.parkingMeterId = parkingMeterId;
    }

    public Integer getParkingMeterId() {
        return parkingMeterId;
    }

    public Set<Tickets> getTicketsSet() {
        return ticketsSet;
    }

    public void setTicketsSet(Set<Tickets> ticketsSet) {
        this.ticketsSet = ticketsSet;
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

    public ParkingMeter() {
    }
}
