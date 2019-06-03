package SOA.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int parkingSpotId;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    @OneToMany
    @JoinColumn(name = "parkingSpotId")
    private List<Availability> availabilityList;

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(int parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }
}
