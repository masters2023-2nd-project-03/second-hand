package team03.secondhand.oauth2.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {
    private String nickname;
    private String profileUrl;
    private String oauthId;

    @Builder
    public MemberDto(String nickname, String profileUrl, String oauthId) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
    }
}
