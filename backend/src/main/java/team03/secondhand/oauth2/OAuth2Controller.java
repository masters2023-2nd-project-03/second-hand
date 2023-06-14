package team03.secondhand.oauth2;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.member.Member;
import team03.secondhand.oauth2.dto.MemberDto;
import team03.secondhand.oauth2.dto.response.ResponseLoginSuccess;
import team03.secondhand.oauth2.error.RequireRegistrationError;

import java.io.IOException;
import java.util.Optional;
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
    public ResponseEntity<ResponseLoginSuccess> login(@RequestParam("platform") String platform, @RequestParam("code") String code) throws IOException, ExecutionException, InterruptedException {
        String accessToken = oAuth2Service.getAccessToken(platform, code);

        // TODO : authorization code 가 유효하지 않을 수 있다.
        MemberDto memberDto = oAuth2Service.getMemberEntity(platform, accessToken);
        Optional<Member> memberOptional = oAuth2Service.findMemberByOauthId(memberDto.getOauthId());

        if (memberOptional.isEmpty()) {
            throw new RequireRegistrationError(memberDto);
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
