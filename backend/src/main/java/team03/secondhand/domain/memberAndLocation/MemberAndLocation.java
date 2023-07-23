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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_location_id")
    private Long memberLocationId;

    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "main_location_status")
    private boolean mainLocationState;

    public MemberAndLocation(Member member, Location location) {
        this.member = member;
        this.location = location;
    }

    public long getLocationId() {
        return location.getLocationId();
    }

    public String getLocationDetails() {
        return location.getLocationDetails();
    }

    public String getLocationShortening() {
        return  location.getLocationShortening();
    }

    public boolean isMainLocation() {
        return mainLocationState;
    }

}
