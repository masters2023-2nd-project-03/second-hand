package team03.secondhand.domain.member.dto.request;


import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RequestJoinDto {
    private String nickname;
    private String profileUrl;
    private String oauthId;

    private List<Long>  locationIdList = new ArrayList<>();

    @Builder
    public RequestJoinDto(String nickname, String profileUrl, String oauthId, Long... locations) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.oauthId = oauthId;
        locationIdList.addAll(List.of());
    }
}
