package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.member.dto.request.RequestJoinDto;
import team03.secondhand.domain.member.dto.response.ResponseJoinSuccessDto;
import team03.secondhand.domain.member.dto.response.ResponseMemberDTO;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping
    public ResponseEntity<ResponseMemberDTO> getMember(@RequestParam String oauthId) {
        log.info("getMember");
        return ResponseEntity.ok(memberService.getMemberByOAuthId(oauthId));
    }

    @PostMapping("/join")
    public ResponseEntity<ResponseJoinSuccessDto> join(@RequestBody RequestJoinDto requestJoinDto) {
        if (memberService.isRegistrationBy(requestJoinDto.getOauthId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 회원가입된 맴버입니다.");
        }

        Member savedMember = memberService.save(requestJoinDto);
        String jwt = createToken(savedMember);

        return ResponseEntity.ok(
                ResponseJoinSuccessDto.builder()
                        .jwt(jwt)
                        .build());
    }

    private String createToken(Member savedMember) {
        String memberId = String.valueOf(savedMember.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
