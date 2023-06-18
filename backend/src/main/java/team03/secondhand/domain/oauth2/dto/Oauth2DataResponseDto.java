package team03.secondhand.domain.oauth2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.dto.MemberDataResponseDto;

@Data
@NoArgsConstructor
public class Oauth2DataResponseDto {

    @Data
    public static class AuthorizationUrl {
        private String url;

        public AuthorizationUrl(String url) {
            this.url = url;
        }
    }

    @Data
    @NoArgsConstructor
    public static class LoginInfo extends MemberDataResponseDto.Join {

        public LoginInfo(Member member, String jwt) {
            super(member, jwt);
        }
    }

}
