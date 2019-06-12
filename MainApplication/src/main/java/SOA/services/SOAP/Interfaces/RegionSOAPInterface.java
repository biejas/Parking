package SOA.services.SOAP.Interfaces;

import SOA.DTO.RegionDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface RegionSOAPInterface {
    @WebMethod
    List<RegionDTO> getRegions();

    @WebMethod
    long countNotAvailableSpotsAtRegion(Integer id);

    @WebMethod
    long countAvailableSpotsAtRegion(Integer id);
}
