package team03.secondhand.domain.member.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMemberDTO {
    private Long memberId;
    private String nickname;
    private String profileUrl;

    @Builder
    public ResponseMemberDTO(Long memberId, String nickname, String profileUrl) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
    // Getter and Setter methods
}
