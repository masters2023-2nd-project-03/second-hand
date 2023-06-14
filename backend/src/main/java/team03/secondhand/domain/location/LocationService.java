package team03.secondhand.domain.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.location.dto.response.ResponseLocationDTO;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public List<ResponseLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ResponseLocationDTO> getAllLocationsByLocationDetailsLike(String searchKey) {
        List<Location> locations = locationRepository.findAllByLocationDetailsLike("%" + searchKey + "%");
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ResponseLocationDTO convertToDTO(Location location) {
        return ResponseLocationDTO.builder()
                .locationId(location.getLocationId())
                .locationDetails(location.getLocationDetails())
                .locationShortening(location.getLocationShortening())
                .build();
    }
}
