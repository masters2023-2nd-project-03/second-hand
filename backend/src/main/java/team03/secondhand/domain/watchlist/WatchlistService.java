package team03.secondhand.domain.watchlist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberRepository;
import team03.secondhand.domain.product.Product;
import team03.secondhand.domain.product.ProductRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addToWatchlist(Long productId, Long memberId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("Product not found with ID: " + productId));
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("Member not found with ID: " + memberId));

        Watchlist watchlist = Watchlist.builder()
                .product(product)
                .member(member)
                .build();

        watchlistRepository.save(watchlist);
    }
}
