package team03.secondhand.oauth2.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseLoginSuccess {

    private String jwt;

    public ResponseLoginSuccess(String jwt) {
        this.jwt = jwt;
    }

}
