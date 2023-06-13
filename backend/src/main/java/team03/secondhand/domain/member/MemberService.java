package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.member.dto.response.ResponseMemberDTO;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseMemberDTO getMemberByOAuthId(String oauthId) {
        Member member = memberRepository.findByOauthId(oauthId).orElse(null);

        return ResponseMemberDTO.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}
