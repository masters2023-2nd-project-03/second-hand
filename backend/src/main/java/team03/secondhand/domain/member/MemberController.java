package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.member.dto.response.ResponseMemberDTO;

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
}
