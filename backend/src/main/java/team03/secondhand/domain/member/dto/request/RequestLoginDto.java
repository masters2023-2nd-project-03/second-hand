package team03.secondhand.domain.member.dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestLoginDto {
    private String platform;
    private String code;

    @Builder
    public RequestLoginDto(String platform, String code) {
        this.platform = platform;
        this.code = code;
    }
}
