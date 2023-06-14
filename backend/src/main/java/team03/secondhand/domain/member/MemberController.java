package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.member.dto.response.ResponseMemberDTO;
import team03.secondhand.oauth2.dto.MemberDto;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<ResponseMemberDTO> getMember(@RequestParam String oauthId) {
        log.info("getMember");
        return ResponseEntity.ok(memberService.getMemberByOAuthId(oauthId));
    }

    @PostMapping("/join")
    public String join(@RequestBody MemberDto memberDto) {
        if (memberService.isRegistrationBy(memberDto.getOauthId())) {
            return "fail";
        }

        Member member = Member.builder()
                .nickname(memberDto.getNickname())
                .profileUrl(memberDto.getProfileUrl())
                .oauthId(memberDto.getOauthId())
                .build();
        memberService.save(member);
        return "ok";
    }

}
