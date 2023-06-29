package team03.secondhand.domain.oauth2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.dto.MemberDataResponseDto;

@Getter @Setter
@NoArgsConstructor
public class Oauth2DataResponseDto {

    @Getter @Setter
    public static class AuthorizationUrl {
        private String url;

        public AuthorizationUrl(String url) {
            this.url = url;
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    public static class LoginInfo extends MemberDataResponseDto.Join {

        public LoginInfo(Member member, String jwt) {
            super(member, jwt);
        }
    }

}
