package team03.secondhand.domain.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.location.dto.LocationResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public List<LocationResponseDTO.Info> getAllLocations(String searchKey) {
        List<Location> locations;
        if (searchKey == null) {
            log.debug("모두 출력");
            locations = locationRepository.findAll();
        } else {
            log.debug("필터 출력");
            locations = locationRepository.findAllByLocationDetailsContains(searchKey);
        }
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LocationResponseDTO.Info convertToDTO(Location location) {
        return new LocationResponseDTO.Info(location);
    }
}
