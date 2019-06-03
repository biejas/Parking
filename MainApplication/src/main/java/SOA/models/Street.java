package SOA.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer streetId;

    @NotNull
    private String streetName;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToMany(mappedBy = "street")
    private Set<ParkingSpot> parkingSpotSet;

    @OneToMany(mappedBy = "street")
    private Set<ParkingMeter> parkingMeterSet;

    public Integer getStreetId() {
        return streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<ParkingSpot> getParkingSpotSet() {
        return parkingSpotSet;
    }

    public void setParkingSpotSet(Set<ParkingSpot> parkingSpotSet) {
        this.parkingSpotSet = parkingSpotSet;
    }

    public Set<ParkingMeter> getParkingMeterSet() {
        return parkingMeterSet;
    }

    public void setParkingMeterSet(Set<ParkingMeter> parkingMeterSet) {
        this.parkingMeterSet = parkingMeterSet;
    }
}
