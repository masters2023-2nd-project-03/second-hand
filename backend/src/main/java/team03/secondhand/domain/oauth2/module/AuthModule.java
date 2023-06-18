package team03.secondhand.domain.oauth2.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import team03.secondhand.domain.oauth2.dto.Oauth2Data;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 인증 모듈 추상 클래스
 *
 * @author RWB
 * @since 2021.09.29 Wed 23:30:47
 */
abstract public class AuthModule extends DefaultApi20 {
    protected OAuth20Service service;

    protected AuthModule(String apiKey, String secretKey, String callbackUrl) {
        service = new ServiceBuilder(apiKey).apiSecret(secretKey).callback(callbackUrl).build(this);
    }

    abstract protected String getMemberInfoEndPoint();

    abstract public Oauth2Data.LoginInfo getLoginInfo(String body) throws JsonProcessingException;

    public String getAuthorizationUrl() {
        return service.getAuthorizationUrl();
    }

    public OAuth2AccessToken getAccessToken(String code) throws IOException, ExecutionException, InterruptedException {
        return service.getAccessToken(code);
    }

    public Response getMemberInfoResponse(OAuth2AccessToken oAuth2AccessToken) throws IOException, ExecutionException, InterruptedException {
        String accessToken = oAuth2AccessToken.getAccessToken();
        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, getMemberInfoEndPoint());
        service.signRequest(accessToken, oAuthRequest);
        return service.execute(oAuthRequest);
    }

}
