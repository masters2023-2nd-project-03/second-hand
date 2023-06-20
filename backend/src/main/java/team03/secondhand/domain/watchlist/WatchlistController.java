package team03.secondhand.domain.watchlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.watchlist.dto.request.RequestWatchlistDTO;

@Slf4j
@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping
    public ResponseEntity<String> addToWatchlist(@RequestBody RequestWatchlistDTO addToWatchlistDTO) {
        try {
            watchlistService.addToWatchlist(addToWatchlistDTO.getMemberId(), addToWatchlistDTO.getProductId());
            return ResponseEntity.ok("Added to watchlist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add to watchlist");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteToWatchlist(@RequestBody RequestWatchlistDTO deleteToWatchlistDTO) {
        try {
            watchlistService.deleteToWatchlist(deleteToWatchlistDTO.getMemberId(), deleteToWatchlistDTO.getProductId());
            return ResponseEntity.ok("Deleted from watchlist\"");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete from watchlist");
        }
    }
}
