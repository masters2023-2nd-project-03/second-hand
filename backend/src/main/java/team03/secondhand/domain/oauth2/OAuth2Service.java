package team03.secondhand.domain.oauth2;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberRepository;
import team03.secondhand.domain.oauth2.dto.Oauth2Data;
import team03.secondhand.domain.oauth2.dto.Oauth2DataRequest;
import team03.secondhand.domain.oauth2.dto.Oauth2DataResponse;
import team03.secondhand.domain.oauth2.error.RequireRegistrationError;
import team03.secondhand.domain.oauth2.module.AuthModule;
import team03.secondhand.domain.oauth2.module.GithubAuthModule;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    private final MemberRepository memberRepository;
    private final GithubAuthModule githubAuthModule;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public OAuth2Service(GithubAuthModule githubAuthModule, MemberRepository memberRepository,  JwtTokenProvider jwtTokenProvider) {
        this.githubAuthModule = githubAuthModule;
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Oauth2DataResponse.AuthorizationUrl authorizationUrlResponse(String platform) {
        AuthModule authModule = getAuthModule(platform);
        return new Oauth2DataResponse.AuthorizationUrl(authModule.getAuthorizationUrl());
    }

    public Oauth2Data.LoginInfo getLoginInfo(Oauth2DataRequest.Login requestLoginDto) throws IOException, ExecutionException, InterruptedException {
        AuthModule authModule = getAuthModule(requestLoginDto.getPlatform());

        // TODO: 아래의 코드는 가독성이 매우 떨어진다.
        Response memberInfo = null;
        try {
            OAuth2AccessToken oAuth2AccessToken = authModule.getAccessToken(requestLoginDto.getCode());
            memberInfo = authModule.getMemberInfo(oAuth2AccessToken);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
       try {
           return authModule.getMemberLoginInfo(memberInfo.getBody());
       } catch (Exception e) {
           throw new InvalidPropertiesFormatException(e);
       }
    }

    public Oauth2DataResponse.LoginInfo findMember(Oauth2Data.LoginInfo loginInfo) {
        Member member = memberRepository.findByOauthId(loginInfo.getOauthId())
                .orElseThrow(() -> new RequireRegistrationError(loginInfo));
        String jwt = getJwtByMember(member);
        return new Oauth2DataResponse.LoginInfo(member, jwt);
    }

    private AuthModule getAuthModule(String platform) {
        // TODO : 인증 서비스 모듈이 늘어날 수 있다.
        return githubAuthModule;
    }

    private String getJwtByMember(Member member) {
        String memberId = String.valueOf(member.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
