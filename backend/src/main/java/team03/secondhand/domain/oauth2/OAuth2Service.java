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
import team03.secondhand.domain.oauth2.error.Oauth2Error;
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

    /*
    /**********************************************************************
    /* Public Method
    /**********************************************************************
     */

    public Oauth2DataResponse.AuthorizationUrl authorizationUrlResponse(String platform) {
        AuthModule authModule = getAuthModule(platform);
        return new Oauth2DataResponse.AuthorizationUrl(authModule.getAuthorizationUrl());
    }

    public Oauth2Data.LoginInfo getLoginInfo(Oauth2DataRequest.Login requestLoginDto) throws IOException, ExecutionException, InterruptedException {
        AuthModule authModule = getAuthModule(requestLoginDto.getPlatform());
        OAuth2AccessToken oAuth2AccessToken = getoAuth2AccessToken(authModule, requestLoginDto);
        Response memberInfoResponse = getMemberInfoResponse(authModule, oAuth2AccessToken);
        return getLoginInfo(authModule, memberInfoResponse);
    }

    public Oauth2DataResponse.LoginInfo findMember(Oauth2Data.LoginInfo loginInfo) {
        Member member = memberRepository.findByOauthId(loginInfo.getOauthId())
                .orElseThrow(() -> new Oauth2Error.RequireRegistration(loginInfo));
        String jwt = getJwtByMember(member);
        return new Oauth2DataResponse.LoginInfo(member, jwt);
    }

    /*
    /**********************************************************************
    /* Private Method
    /**********************************************************************
     */

    private static Oauth2Data.LoginInfo getLoginInfo(AuthModule authModule, Response memberInfoResponse) throws InvalidPropertiesFormatException {
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

    private static OAuth2AccessToken getoAuth2AccessToken(AuthModule authModule, Oauth2DataRequest.Login requestLoginDto) throws IOException, ExecutionException, InterruptedException {
        try {
            return authModule.getAccessToken(requestLoginDto.getCode());
        } catch (Exception e) {
            throw new Oauth2Error.TokenInvalid();
        }
    }

    private AuthModule getAuthModule(String platform){
        if (platform.equals("github")) {
            return githubAuthModule;
        }
        throw new Oauth2Error.NotFoundPlatform();
    }

    private String getJwtByMember(Member member) {
        String memberId = String.valueOf(member.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
