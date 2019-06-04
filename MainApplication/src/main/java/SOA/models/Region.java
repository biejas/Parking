package SOA.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer regionId;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "region")
    private Set<Employee> employee;

    @OneToMany(mappedBy = "region")
    private Set<ParkingSpot> parkingSpotSet;

    @OneToMany(mappedBy = "region")
    private Set<ParkingMeter> parkingMeterSet;

    public int getRegionId() {
        return regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
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
