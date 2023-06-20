package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.member.dto.request.RequestJoinDto;
import team03.secondhand.domain.member.dto.response.ResponseJoinSuccessDto;
import team03.secondhand.domain.member.dto.response.ResponseShowSuccessDto;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/join")
    public ResponseEntity<ResponseJoinSuccessDto> join(@RequestBody RequestJoinDto requestJoinDto) {
        if (memberService.isRegistrationBy(requestJoinDto.getOauthId())) {
            throw new DataIntegrityViolationException("");
        }

        Member member = memberService.join(requestJoinDto);
        String jwt = createToken(member);
        return ResponseEntity.ok(
                ResponseJoinSuccessDto.builder()
                        .jwt(jwt)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ResponseShowSuccessDto> show(HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        Member member = memberService.getMemberById(memberId)
                .orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(
                ResponseShowSuccessDto.builder()
                        .member(member)
                        .build());
    }

    private String createToken(Member savedMember) {
        String memberId = String.valueOf(savedMember.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
