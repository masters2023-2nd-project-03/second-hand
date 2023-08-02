package team03.secondhand.domain.oauth2.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import team03.secondhand.Util;
import team03.secondhand.domain.oauth2.dto.Oauth2DataDto;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class GithubAuthModule extends AuthModule {

    private static final String MODULE_NAME = "github";

    public GithubAuthModule(String apiKey, String secretKey, String callbackUrl) {
        super(apiKey, secretKey, callbackUrl);
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://github.com/login/oauth/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://github.com/login/oauth/authorize";
    }

    @Override
    protected String getMemberInfoEndPoint() {
        return "https://api.github.com/user";
    }

    @Override
    public OAuth2AccessToken getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", service.getApiKey());
        params.add("client_secret", service.getApiSecret());
        params.add("code", code);
        params.add("redirect_uri", service.getCallback());
        params.add("Accept", "application/json");
        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(params, headers);
        ResponseEntity<String> accessTokenResponse = restTemplate.exchange(
                getAccessTokenEndpoint(),
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );

        String body = accessTokenResponse.getBody();

        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUriString("?" + body).build().getQueryParams();
        String accessToken = queryParams.getFirst("access_token");
        String tokenType = queryParams.getFirst("token_type");
        String scope = queryParams.getFirst("scope");
        return new OAuth2AccessToken(accessToken, tokenType, 0, null, scope, body);
    }

    @Override
    public Response getMemberInfoResponse(OAuth2AccessToken oAuth2AccessToken) throws IOException, ExecutionException, InterruptedException {
        String accessToken = oAuth2AccessToken.getAccessToken();
        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, getMemberInfoEndPoint());
        oAuthRequest.addHeader("Authorization", Util.builder("Bearer ", accessToken));
        service.signRequest(accessToken, oAuthRequest);
        return service.execute(oAuthRequest);
    }

    @Override
    public Oauth2DataDto.LoginInfo getLoginInfo(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(body);

        String nickname = node.get("name") == null ? "미동의" : node.get("name").textValue();
        Long id = node.get("id") == null ? 0 : node.get("id").longValue();
        String profileUrl = node.get("avatar_url") == null ? "미동의" : node.get("avatar_url").textValue();

        return new Oauth2DataDto.LoginInfo(
                nickname
                , profileUrl
                , MODULE_NAME + "_" + id);
    }

    public Boolean isProcessPossible(String platform) {
        return MODULE_NAME.equals(platform);
    }
}
