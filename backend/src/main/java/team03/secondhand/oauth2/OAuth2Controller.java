package team03.secondhand.oauth2;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.member.Member;
import team03.secondhand.oauth2.dto.MemberDto;
import team03.secondhand.oauth2.dto.response.ResponseLogin;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @Autowired
    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @GetMapping("/{platform}")
    public String authorizationUrlResponse(@PathVariable("platform") String platform) {
        return oAuth2Service.authorizationUrlResponse(platform);
    }

    @GetMapping("/access")
    public String getAccessToken(@RequestParam("platform") String platform, @RequestParam("code") String code) throws IOException, ExecutionException, InterruptedException {
        return oAuth2Service.getAccessToken(platform, code);
    }

    @GetMapping("/login")
    public ResponseLogin login(@RequestParam("platform") String platform, @RequestParam("access") String access) throws IOException, ExecutionException, InterruptedException {
        MemberDto memberDto = oAuth2Service.getMemberEntity(platform, access);
        Optional<Member> memberOptional = oAuth2Service.findMemberByOauthId(memberDto.getOauthId());

        if (memberOptional.isEmpty()) {
            return new ResponseLogin(
                    memberDto.getNickname(),
                    memberDto.getProfileUrl()
                    ,memberDto.getOauthId());
        }

        // TODO : jwt 비밀키가 단순하다. 대체 고려
        return new ResponseLogin(
                Jwts.builder()
                .setSubject(memberDto.getOauthId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, platform)
                .compact());
    }

}
