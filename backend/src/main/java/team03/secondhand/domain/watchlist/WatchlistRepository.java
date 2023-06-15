package team03.secondhand.domain.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Watchlist findByProduct_ProductIdAndMember_MemberId(Long productId, Long memberId);
}
