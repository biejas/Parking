package SOA.services.SOAP.Implementations;

import SOA.DTO.ParkingSpotDTO;
import SOA.DTO.RegionDTO;
import SOA.services.ParkingSpotService;
import SOA.services.RegionService;
import SOA.services.SOAP.Interfaces.RegionSOAPInterface;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "SOA.services.SOAP.Interfaces.RegionSOAPInterface")
public class RegionSOAPService implements RegionSOAPInterface {
     @EJB
    private RegionService regionService = new RegionService();

     @EJB
     private ParkingSpotService parkingSpotService;

    @Override
    public List<RegionDTO> getRegions() {
        return regionService.getRegionDTO();
    }

    @Override
    public long countNotAvailableSpotsAtRegion(Integer id) {
        return parkingSpotService
                .getParkingSpotDTOByRegion(id)
                .stream()
                .filter(x -> !x.isAvailable())
                .count();
    }

    @Override
    public long countAvailableSpotsAtRegion(Integer id) {
        return parkingSpotService
                .getParkingSpotDTOByRegion(id)
                .stream()
                .filter(ParkingSpotDTO::isAvailable)
                .count();
    }
}
