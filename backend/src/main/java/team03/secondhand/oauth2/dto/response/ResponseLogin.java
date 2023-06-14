package team03.secondhand.oauth2.dto.response;

import lombok.Getter;

@Getter
// TODO : 하나의 DTO 두 가지 이상의 일을 하고 있다.
public class ResponseLogin {

    private String nickname;
    private String profileUrl;
    private String oauthId;
    private String jwt;

    private String state;

    public ResponseLogin(String nickname, String profileUrl, String oauthId) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
        this.state = "require join";
    }

    public ResponseLogin(String jwt) {
        this.jwt = jwt;
        this.state = "login success";
    }
}
