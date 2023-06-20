package team03.secondhand.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseJoinSuccessDto {
    private String jwt;

    @Builder
    public ResponseJoinSuccessDto(String jwt) {
        this.jwt = jwt;
    }

}
