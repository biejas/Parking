package SOA.services;

import SOA.DAO.RegionDAO;
import SOA.DTO.RegionDTO;
import SOA.utils.DTOUtils;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Startup
@Transactional
public class RegionService {
    @EJB
    private RegionDAO regionDAO;

    public RegionDTO getRegionDTO(Integer id){
        return regionDAO.getRegionById(id)
                .map(DTOUtils::changeRegionToRegionDTO)
                .orElseThrow(RuntimeException::new);
    }

    public List<RegionDTO> getRegionDTO(){
        return regionDAO.getRegions()
                .stream()
                .map(DTOUtils::changeRegionToRegionDTO)
                .collect(Collectors.toList());
    }
}
