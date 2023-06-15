package team03.secondhand.domain.watchlist.dto.request;

import lombok.Getter;

@Getter
public class RequestAddToWatchlistDTO {

    private Long productId;
    private Long memberId;
}
