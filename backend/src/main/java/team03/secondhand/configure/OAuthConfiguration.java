package team03.secondhand.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team03.secondhand.domain.oauth2.module.GithubAuthModule;
import team03.secondhand.domain.oauth2.module.GithubIosAuthModule;

@Configuration
public class OAuthConfiguration {

    // 웹 깃허브 인증

    @Value("${github_client_id}")
    private String githubApiKey;
    @Value("${github_client_secret}")
    private String githubSecretKey;
    @Value("${github_redirect_uri}")
    private String githubCallbackUrl;

    @Bean
    public GithubAuthModule githubAuthModule() {
        return new GithubAuthModule(githubApiKey, githubSecretKey, githubCallbackUrl);
    }

    // iOS 깃 허브 인증
    @Value("${github_iOS_client_id}")
    private String githubIosApiKey;
    @Value("${github_iOS_client_secret}")
    private String githubIosSecretKey;
    @Value("${github_iOS_redirect_uri}")
    private String githubIosCallbackUrl;

    @Bean
    public GithubIosAuthModule githubIosAuthModule() {
        return new GithubIosAuthModule(githubIosApiKey, githubIosSecretKey, githubIosCallbackUrl);
    }

}
