package SOA.models;


import javax.persistence.*;

@Entity
public class ParkingMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int parkingMeterId;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    public int getParkingMeterId() {
        return parkingMeterId;
    }

    public void setParkingMeterId(int parkingMeterId) {
        this.parkingMeterId = parkingMeterId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
