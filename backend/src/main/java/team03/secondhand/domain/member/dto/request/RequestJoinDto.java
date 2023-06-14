package team03.secondhand.domain.member.dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestJoinDto {
    private String nickname;
    private String profileUrl;
    private String oauthId;

    @Builder
    public RequestJoinDto(String nickname, String profileUrl, String oauthId) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
    }
}
