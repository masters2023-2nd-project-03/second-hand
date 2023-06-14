package team03.secondhand.domain.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.location.dto.response.ResponseLocationDTO;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<ResponseLocationDTO>> getAllLocations(@RequestParam("search-key") String searchKey) {
        if (searchKey == null) {
            log.debug("동네 목록 호출(ALL)");
            return ResponseEntity.ok(locationService.getAllLocations());
        } else {
            log.debug("동네 목록 호출(필터)");
            return ResponseEntity.ok(locationService.getAllLocationsByLocationDetailsLike(searchKey));
        }
    }
}
