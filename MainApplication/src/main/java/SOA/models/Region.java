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
    private Set<Street> streetList;


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

    public Set<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(Set<Street> streetList) {
        this.streetList = streetList;
    }
}
