package team03.secondhand.domain.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Watchlist findByProduct_ProductIdAndMember_MemberId(Long productId, Long memberId);

    List<Watchlist> findAllByProduct_ProductId(Long productId);
}
