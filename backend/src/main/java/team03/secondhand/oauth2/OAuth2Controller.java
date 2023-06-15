package team03.secondhand.oauth2;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.dto.request.RequestLoginDto;
import team03.secondhand.oauth2.dto.MemberDto;
import team03.secondhand.oauth2.dto.response.ResponseLoginSuccess;
import team03.secondhand.oauth2.error.RequireRegistrationError;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{platform}")
    public String authorizationUrlResponse(@PathVariable("platform") String platform) {
        return oAuth2Service.authorizationUrlResponse(platform);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseLoginSuccess> login(RequestLoginDto requestLoginDto) throws InvalidKeyException, IOException, ExecutionException, InterruptedException {
        Member member = null;

        // TODO: 딱 봐도 깔끔하지 않
        try {
            String accessToken = oAuth2Service.getAccessToken(requestLoginDto.getPlatform(), requestLoginDto.getCode());
            MemberDto memberDto = oAuth2Service.getMemberEntity(requestLoginDto.getPlatform(), accessToken);
            member = oAuth2Service.findMemberByOauthId(memberDto.getOauthId())
                    .orElseThrow(() -> new RequireRegistrationError(memberDto));
        } catch (Exception e) {
            throw new InvalidKeyException();
        }

        String jwt = getJwtByOptionalMember(memberOptional);
        return ResponseEntity.ok(new ResponseLoginSuccess(jwt));
    }

    private String getJwtByOptionalMember(Optional<Member> memberOptional) {
        String memberId = String.valueOf(memberOptional.get().getMemberId());
        String token = jwtTokenProvider.createToken(memberId);
        return token;
    }

}
