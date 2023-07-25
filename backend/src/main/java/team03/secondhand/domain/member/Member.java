package team03.secondhand.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.member.model.MemberAndLocations;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;
import team03.secondhand.domain.product.Product;
import team03.secondhand.domain.watchlist.Watchlist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Embedded
    private MemberAndLocations memberAndLocations;

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

    public void changeLocation(List<Location> locations, int mainLocationIndex) {
        memberAndLocations.changeLocation(locations, mainLocationIndex);
    }

    public List<MemberAndLocation> getMemberAndLocationList() {
        return memberAndLocations.getMemberAndLocationList();
    }

}
