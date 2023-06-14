package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.member.dto.response.ResponseMemberDTO;

import java.util.Optional;


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
    public Optional<Member> findByOauthId(String oauthId) {
        return memberRepository.findByOauthId(oauthId);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public boolean isRegistrationBy(String oauthId) {
        Optional<Member> optionalMember = memberRepository.findByOauthId(oauthId);
        return optionalMember.isPresent();
    }

}
