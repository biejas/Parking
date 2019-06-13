package spot.soa.services.soap;

import spot.soa.services.soap.implementations.ParkingSpotSOAPInterface;
import spot.soa.services.soap.implementations.ParkingSpotSOAPService;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ParkingSpotController {
    private ParkingSpotSOAPInterface parkingSpotSOAPService = getParkingSpotSOAPService();
    private Integer parkingSpotId=0;

    public void take() {
        parkingSpotSOAPService.takeSpot(parkingSpotId);
    }

    public void leave() {
        parkingSpotSOAPService.leaveSpot(parkingSpotId);
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(int parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    private ParkingSpotSOAPInterface getParkingSpotSOAPService() {
        return new ParkingSpotSOAPService().getParkingSpotSOAPServicePort();
    }
}
