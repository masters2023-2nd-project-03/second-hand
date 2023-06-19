package team03.secondhand.domain.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MemberDataRequestDto {
    @Getter @Setter
    public static class Join {
        private String nickname;
        private String profileUrl;
        private String oauthId;
        private List<Long> locationIdList = new ArrayList<>();

    }

    @Getter @Setter
    public static class UpdateLocation {
        private List<Long> locationIdList = new ArrayList<>();

    }

}
