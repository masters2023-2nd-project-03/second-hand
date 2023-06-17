package team03.secondhand.domain.oauth2;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.oauth2.dto.Oauth2Data;
import team03.secondhand.domain.oauth2.dto.Oauth2DataRequest;
import team03.secondhand.domain.oauth2.dto.Oauth2DataResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/{platform}")
    public DataResponse<Oauth2DataResponse.AuthorizationUrl> authorizationUrlResponse(@PathVariable("platform") String platform) {
        Oauth2DataResponse.AuthorizationUrl authorizationUrl = oAuth2Service.authorizationUrlResponse(platform);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, authorizationUrl);
    }

    @GetMapping("/login")
    public DataResponse<Oauth2DataResponse.LoginInfo> login(Oauth2DataRequest.Login requestLoginDto) throws Exception {
        Oauth2Data.LoginInfo loginInfo = oAuth2Service.getLoginInfo(requestLoginDto);
        Oauth2DataResponse.LoginInfo oauth2DataResponse = oAuth2Service.findMember(loginInfo);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, oauth2DataResponse);
    }

}
