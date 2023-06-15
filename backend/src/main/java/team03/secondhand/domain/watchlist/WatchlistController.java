package team03.secondhand.domain.watchlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.watchlist.dto.request.RequestAddToWatchlistDTO;

@Slf4j
@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping
    public ResponseEntity<?> addToWatchlist(@RequestBody RequestAddToWatchlistDTO addToWatchlistDTO) {
        try {
            watchlistService.addToWatchlist(addToWatchlistDTO.getMemberId(), addToWatchlistDTO.getProductId());
            return ResponseEntity.ok("Added to watchlist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add to watchlist");
        }
    }
}
