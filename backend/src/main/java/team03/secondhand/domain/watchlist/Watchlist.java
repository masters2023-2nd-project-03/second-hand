package team03.secondhand.domain.watchlist;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "watchlist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watchlist_id")
    private Long watchlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Watchlist(Product product, Member member) {
        this.product = product;
        this.member = member;
    }

    public boolean isWatchedByMemberId(Long memberId) {
        return this.member.getMemberId().equals(memberId);
    }

}
