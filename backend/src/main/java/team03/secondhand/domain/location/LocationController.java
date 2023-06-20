package team03.secondhand.domain.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.location.dto.LocationResponseDTO;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public DataResponse<List<LocationResponseDTO.Info>> getAllLocations(@RequestParam(value = "search-key", required = false) String searchKey) {
        log.debug("동네 목록 호출");
        List<LocationResponseDTO.Info> locationInfoList = locationService.getAllLocations(searchKey);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, locationInfoList);
    }
}
