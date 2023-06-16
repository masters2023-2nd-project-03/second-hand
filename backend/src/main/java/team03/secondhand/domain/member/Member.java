package team03.secondhand.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "member" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MemberAndLocation> memberAndLocationList = new ArrayList<>();

    public void add(MemberAndLocation memberAndLocation) {
        memberAndLocationList.add(memberAndLocation);
    }

    @Builder
    public Member(String nickname, String profileUrl, String oauthId) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Product> products = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Watchlist> watchlistList = new ArrayList<>();

}
