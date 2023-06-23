package team03.secondhand.domain.watchlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.watchlist.dto.WatchlistDataRequestDTO;

@Slf4j
@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping
    public DataResponse<String> addToWatchlist(@RequestAttribute Long memberId, @RequestBody WatchlistDataRequestDTO addToWatchlistDTO) {
        watchlistService.addToWatchlist(addToWatchlistDTO.getProductId(), memberId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, "Added to watchlist");
    }

    @DeleteMapping
    public DataResponse<String> deleteToWatchlist(@RequestAttribute Long memberId, @RequestBody WatchlistDataRequestDTO deleteToWatchlistDTO) {
        watchlistService.deleteToWatchlist(deleteToWatchlistDTO.getProductId(), memberId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, "Deleted from watchlist");
    }
}
