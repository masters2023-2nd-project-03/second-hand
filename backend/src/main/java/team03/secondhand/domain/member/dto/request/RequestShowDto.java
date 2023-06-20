package team03.secondhand.domain.member.dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestShowDto {
    private String jwt;

    @Builder
    public RequestShowDto(String jwt) {
        this.jwt = jwt;
    }
}
