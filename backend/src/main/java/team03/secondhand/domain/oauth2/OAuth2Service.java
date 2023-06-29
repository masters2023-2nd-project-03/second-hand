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
import team03.secondhand.domain.oauth2.dto.Oauth2DataDto;
import team03.secondhand.domain.oauth2.dto.Oauth2DataRequestDto;
import team03.secondhand.domain.oauth2.dto.Oauth2DataResponseDto;
import team03.secondhand.domain.oauth2.module.AuthModule;
import team03.secondhand.domain.oauth2.module.GithubAuthModule;
import team03.secondhand.domain.oauth2.module.GithubIosAuthModule;
import team03.secondhand.error.Oauth2Error;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    private final MemberRepository memberRepository;
    private final GithubAuthModule githubAuthModule;
    private final GithubIosAuthModule githubIosAuthModule;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public OAuth2Service(GithubAuthModule githubAuthModule,
                         GithubIosAuthModule githubIosAuthModule,
                         MemberRepository memberRepository,
                         JwtTokenProvider jwtTokenProvider) {
        this.githubAuthModule = githubAuthModule;
        this.githubIosAuthModule = githubIosAuthModule;
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /*
    /**********************************************************************
    /* Public Method
    /**********************************************************************
     */

    public Oauth2DataResponseDto.AuthorizationUrl authorizationUrlResponse(String platform) {
        AuthModule authModule = getAuthModule(platform);
        return new Oauth2DataResponseDto.AuthorizationUrl(authModule.getAuthorizationUrl());
    }

    public Oauth2DataDto.LoginInfo getLoginInfo(Oauth2DataRequestDto.Login requestLoginDto) throws IOException, ExecutionException, InterruptedException {
        AuthModule authModule = getAuthModule(requestLoginDto.getPlatform());
        OAuth2AccessToken oAuth2AccessToken = getoAuth2AccessToken(authModule, requestLoginDto);
        Response memberInfoResponse = getMemberInfoResponse(authModule, oAuth2AccessToken);
        return getLoginInfo(authModule, memberInfoResponse);
    }

    public Oauth2DataResponseDto.LoginInfo findMember(Oauth2DataDto.LoginInfo loginInfo) {
        Member member = memberRepository.findByOauthId(loginInfo.getOauthId())
                .orElseThrow(() -> new Oauth2Error.RequireRegistration(loginInfo));
        String jwt = getJwtByMember(member);
        return new Oauth2DataResponseDto.LoginInfo(member, jwt);
    }

    /*
    /**********************************************************************
    /* Private Method
    /**********************************************************************
     */

    private static Oauth2DataDto.LoginInfo getLoginInfo(AuthModule authModule, Response memberInfoResponse) throws InvalidPropertiesFormatException {
        try {
            return authModule.getLoginInfo(memberInfoResponse.getBody());
        } catch (Exception e) {
            throw new Oauth2Error.InvalidPlatform();
        }
    }

    private static Response getMemberInfoResponse(AuthModule authModule, OAuth2AccessToken oAuth2AccessToken) throws IOException, ExecutionException, InterruptedException {
        try {
            return authModule.getMemberInfoResponse(oAuth2AccessToken);
        } catch (Exception e) {
            throw new Oauth2Error.TokenInvalid();
        }
    }

    private static OAuth2AccessToken getoAuth2AccessToken(AuthModule authModule, Oauth2DataRequestDto.Login requestLoginDto) throws IOException, ExecutionException, InterruptedException {
        try {
            return authModule.getAccessToken(requestLoginDto.getCode());
        } catch (Exception e) {
            throw new Oauth2Error.TokenInvalid();
        }
    }

    private AuthModule getAuthModule(String platform) {
        if (platform.equals("github")) {
            return githubAuthModule;
        }
        if (platform.equals("github-iOS")) {
            return githubIosAuthModule;
        }
        throw new Oauth2Error.NotFoundPlatform();
    }

    private String getJwtByMember(Member member) {
        String memberId = String.valueOf(member.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
