package team03.secondhand.domain.oauth2.dto;

import lombok.Data;

@Data
public class Oauth2Data {
    @Data
    public static class LoginInfo {
        private String nickname;
        private String profileUrl;
        private String oauthId;

        public LoginInfo(String nickname, String profileUrl, String oauthId) {
            this.nickname = nickname;
            this.profileUrl = profileUrl;
            this.oauthId = oauthId;
        }
    }
}
