package team03.secondhand.domain.member.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MemberAndLocations {

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberAndLocation> memberAndLocationList = new ArrayList<>();

    public void changeLocation(List<Location> locations, int mainLocationIndex) {
        Member member = memberAndLocationList.get(0).getMember();
        memberAndLocationList.clear();
        memberAndLocationList.addAll(locations.stream()
                .map(location -> new MemberAndLocation(member, location))
                .collect(Collectors.toList()));
        changeMainLocation(mainLocationIndex);
    }

    private void changeMainLocation(int mainLocationIndex) {
        memberAndLocationList.get(mainLocationIndex).setMainLocationState();
    }

    public List<MemberAndLocation> getMemberAndLocationList() {
        return memberAndLocationList;
    }

}
