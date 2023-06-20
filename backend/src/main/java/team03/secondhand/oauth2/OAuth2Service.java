package team03.secondhand.oauth2;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberRepository;
import team03.secondhand.oauth2.dto.MemberDto;
import team03.secondhand.oauth2.module.AuthModule;
import team03.secondhand.oauth2.module.GithubAuthModule;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class OAuth2Service {

    private final GithubAuthModule githubAuthModule;
    private final MemberRepository memberRepository;

    @Autowired
    public OAuth2Service(GithubAuthModule githubAuthModule,  MemberRepository memberRepository) {
        this.githubAuthModule = githubAuthModule;
        this.memberRepository = memberRepository;
    }

    public String authorizationUrlResponse(String platform) {
        AuthModule authModule = getAuthModule(platform);
        return authModule.getAuthorizationUrl();
    }

    public MemberDto getMemberEntity(String platform, String access) throws IOException, ExecutionException, InterruptedException {
        AuthModule authModule = getAuthModule(platform);
        // TODO : 정보를 얻어오는데 실패할 수도 있다.
        Response memberInfo = authModule.getMemberInfo(access);
        String body = memberInfo.getBody();
        return authModule.getMemberEntity(body);
    }

    public Optional<Member> findMemberByOauthId(String oauthId) {
        return memberRepository.findByOauthId(oauthId);
    }

    public String getAccessToken(String platform, String code) throws Exception {
        AuthModule authModule = getAuthModule(platform);
        OAuth2AccessToken oAuth2AccessToken = authModule.getAccessToken(code);
        return oAuth2AccessToken.getAccessToken();
    }

    private AuthModule getAuthModule(String platform) {
        // TODO : 인증 서비스 모듈이 늘어날 수 있다.
        return githubAuthModule;
    }

}
