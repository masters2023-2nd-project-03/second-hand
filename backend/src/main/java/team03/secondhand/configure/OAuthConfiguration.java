package team03.secondhand.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team03.secondhand.oauth2.module.GithubAuthModule;

@Configuration
public class OAuthConfiguration {

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

}
