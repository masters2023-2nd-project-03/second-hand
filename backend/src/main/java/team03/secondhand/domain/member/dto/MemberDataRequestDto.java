package team03.secondhand.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


public class MemberDataRequestDto {

    @Getter @Setter
    public static class Join {
        @NotBlank(message = "닉네임을 설정해주세요")
        @Size(min = 1, max = 60, message = "닉네임의 길이는 1이상 60이하여야 합니다.")
        private String nickname;
        private String profileUrl;
        @NotBlank(message = "잘못된 요청입니다.")
        @Pattern(regexp = "^\\D+_\\d+$", message = "잘못된 OAuth Id 입니다.")
        private String oauthId;
        @Size(min = 1, max = 2, message = "동네의 수는 1이상 2이하입니다.")
        @NotEmpty(message = "동네를 설정해주세요")
        private List<Long> locationIdList = new ArrayList<>();

    }

    @Getter @Setter
    public static class UpdateLocation {
        @Size(min = 1, max = 2, message = "동네의 수는 1이상 2이하입니다.")
        @NotEmpty(message = "동네를 설정해주세요")
        private List<Long> locationIdList = new ArrayList<>();

    }

}
