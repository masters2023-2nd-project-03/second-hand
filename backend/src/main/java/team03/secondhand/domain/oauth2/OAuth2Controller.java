package team03.secondhand.domain.oauth2;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.oauth2.dto.Oauth2DataDto;
import team03.secondhand.domain.oauth2.dto.Oauth2DataRequestDto;
import team03.secondhand.domain.oauth2.dto.Oauth2DataResponseDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/{platform}")
    public DataResponse<Oauth2DataResponseDto.AuthorizationUrl> authorizationUrlResponse(@PathVariable("platform") String platform) {
        Oauth2DataResponseDto.AuthorizationUrl authorizationUrl = oAuth2Service.authorizationUrlResponse(platform);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, authorizationUrl);
    }

    @GetMapping("/login")
    public DataResponse<Oauth2DataResponseDto.LoginInfo> login(@Valid @RequestBody Oauth2DataRequestDto.Login requestLoginDto) throws Exception {
        Oauth2DataDto.LoginInfo loginInfo = oAuth2Service.getLoginInfo(requestLoginDto);
        Oauth2DataResponseDto.LoginInfo oauth2DataResponse = oAuth2Service.findMember(loginInfo);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, oauth2DataResponse);
    }

}
