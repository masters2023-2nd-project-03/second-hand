package team03.secondhand.domain.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.memberAndLocation.dto.LocationData;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberDataResponseDto {

    @Data
    @NoArgsConstructor
    public static class SimpleInfo {
        private String nickname;
        private String profileUrl;

        public SimpleInfo(Member member) {
            this.nickname = member.getNickname();
            this.profileUrl = member.getProfileUrl();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Info extends SimpleInfo {
        private List<LocationData.Info> locationDatas;

        public Info(Member member) {
            super(member);
            this.locationDatas = member.getMemberAndLocationList()
                    .stream()
                    .map(LocationData.Info::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @NoArgsConstructor
    public static class Join extends Info {
        private String jwt;

        public Join(Member member, String jwt) {
            super(member);
            this.jwt = jwt;
        }
    }

}
