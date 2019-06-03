package SOA.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int regionId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @OneToMany
    @JoinColumn(name = "parkingMeterId")
    private List<ParkingMeter> parkingMeterList;


    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ParkingMeter> getParkingMeterList() {
        return parkingMeterList;
    }

    public void setParkingMeterList(List<ParkingMeter> parkingMeterList) {
        this.parkingMeterList = parkingMeterList;
    }
}
