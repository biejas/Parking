package SOA.services.SOAP.Interfaces;

import SOA.DTO.ParkingSpotDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ParkingSpotSOAPInterface {
    @WebMethod
    void takeSpot(Integer id);

    @WebMethod
    void leaveSpot(Integer id);

    @WebMethod
    List<ParkingSpotDTO> getParkingSpots();

    @WebMethod
    List<ParkingSpotDTO> getParkingSpotsByStreet(String street);

    @WebMethod
    List<ParkingSpotDTO> getParkingSpotsByRegion(Integer id);

    @WebMethod
    long countNotAvailableSpotsAtStreet(String street);

    @WebMethod
    long countAvailableSpotsAtStreet(String street);
}
