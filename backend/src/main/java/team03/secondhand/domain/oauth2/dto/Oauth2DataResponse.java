package team03.secondhand.domain.oauth2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.dto.MemberDataResponse;

@Data
@NoArgsConstructor
public class Oauth2DataResponse {

    @Data
    public static class AuthorizationUrl {
        private String url;

        public AuthorizationUrl(String url) {
            this.url = url;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Login extends MemberDataResponse.Join {

        public Login(Member member, String jwt) {
            super(member, jwt);
        }
    }

}
