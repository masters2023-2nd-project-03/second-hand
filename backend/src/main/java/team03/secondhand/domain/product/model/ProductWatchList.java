package team03.secondhand.domain.product.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.watchlist.Watchlist;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ProductWatchList {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private final List<Watchlist> watchlistArrayList = new ArrayList<>();

    public boolean isWatchedByMemberId(Long memberId) {
        for (Watchlist watch : watchlistArrayList) {
            if (watch.isWatchedByMemberId(memberId)) {
                return true;
            }
        }
        return false;
    }

    public int getWatchCount() {
        return watchlistArrayList.size();
    }

}
