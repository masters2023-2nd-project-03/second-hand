package team03.secondhand.domain.memberAndLocation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.member.Member;

import javax.persistence.*;

@Getter
@Table(name = "member_location")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAndLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_location_id")
    private Long memberLocationId;

    @ManyToOne()
    @JoinColumn(name = "member_member_id")
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "location_location_id")
    private Location location;

    @Column(name = "main_status")
    private Boolean isMainLocation;

    public MemberAndLocation(Member member, Location location) {
        this.member = member;
        this.location = location;
    }

}
