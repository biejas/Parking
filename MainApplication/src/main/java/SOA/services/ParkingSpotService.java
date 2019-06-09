package SOA.services;

import SOA.DAO.ParkingSpotDAO;
import SOA.models.Employee;
import SOA.models.ParkingSpot;
import SOA.utils.SecurityUtils;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@Startup
@Transactional
public class ParkingSpotService {
    @EJB
    private ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();

    public ParkingSpot getParkingSpot(int id) {
        return parkingSpotDAO
                .getParkingSpotById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<ParkingSpot> getParkingSpotsForEmployee() {
        Optional<Employee> optionalEmployee = SecurityUtils.getLoggedEmployee();

        if (!optionalEmployee.isPresent()) {
            return Collections.emptyList();
        }

        Employee employee = optionalEmployee.get();

        if (SecurityUtils.isAdmin(employee)) {
            return getParkingSpots();
        } else {
            return getParkingSpots()
                    .stream()
                    .filter(p -> p.getRegion().getRegionId() == employee.getRegion().getRegionId())
                    .collect(Collectors.toList());
        }
    }

    public void updateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpotDAO.updateParkingSpot(parkingSpot);
    }

    private List<ParkingSpot> getParkingSpots() {
        return parkingSpotDAO.getAllParkingSpots();
    }
}
