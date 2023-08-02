package team03.secondhand.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;
import team03.secondhand.domain.product.Product;
import team03.secondhand.domain.watchlist.Watchlist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "oauth_id")
    private String oauthId;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberAndLocation> memberAndLocationList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private final List<Product> products = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private final List<Watchlist> watchlistArrayList = new ArrayList<>();

    @Builder
    public Member(String nickname, String profileUrl, String oauthId) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
    }

    public void addLocation(Location location) {
        memberAndLocationList.add(new MemberAndLocation(this, location));
    }

    public void deleteAllLocation() {
        memberAndLocationList.clear();
    }

}
