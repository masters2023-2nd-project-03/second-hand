package team03.secondhand.domain.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDataRequestDto {
    @Data
    public static class Join {
        private String nickname;
        private String profileUrl;
        private String oauthId;
        private List<Long> locationIdList = new ArrayList<>();

    }

    @Data
    public static class UpdateLocation {
        private List<Long> locationIdList = new ArrayList<>();

    }

}
