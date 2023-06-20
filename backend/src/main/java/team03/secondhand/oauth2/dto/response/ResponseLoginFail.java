package team03.secondhand.oauth2.dto.response;

import lombok.Getter;

@Getter
public class ResponseLoginFail {

    private String nickname;
    private String profileUrl;
    private String oauthId;

    public ResponseLoginFail(String nickname, String profileUrl, String oauthId) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
    }

}
