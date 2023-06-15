package team03.secondhand.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import team03.secondhand.domain.member.Member;

@Getter
public class ResponseShowSuccessDto {
    Member member;
    @Builder
    public ResponseShowSuccessDto(Member member) {
        this.member = member;
    }

}
