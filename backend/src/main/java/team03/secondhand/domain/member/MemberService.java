package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.location.LocationRepository;
import team03.secondhand.domain.member.dto.request.RequestJoinDto;
import team03.secondhand.domain.member.dto.response.ResponseMemberDTO;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    public Optional<Member> getMemberById(Long id) {
        Optional<Member> byMemberId = memberRepository.findByMemberId(id);
        return memberRepository.findByMemberId(id);
    }

    public ResponseMemberDTO getMemberByOAuthId(String oauthId) {
        Member member = memberRepository.findByOauthId(oauthId).orElse(null);

        return ResponseMemberDTO.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    @Transactional
    public Member join(RequestJoinDto requestJoinDto) {
        Member member = Member.builder()
                .nickname(requestJoinDto.getNickname())
                .profileUrl(requestJoinDto.getProfileUrl())
                .oauthId(requestJoinDto.getOauthId())
                .build();
        // TODO: 리펙토링 필요 (JPA 제대로 사용)
        // TODO: 한번에 location 을 찾도록 수정
        // TODO: 데이터 베이스와 관련된 예외를 한 곳에 모으면 좋을 뜻
        List<Long> locationIdList = requestJoinDto.getLocationIdList();
        for (Long locationId : locationIdList) {
            Location location = locationRepository.findById(locationId)
                    .orElseThrow(NoSuchFieldError::new); // 과연 적절한 에러일까?
            MemberAndLocation memberAndLocation = new MemberAndLocation(member, location);
            member.add(memberAndLocation);
        }
        memberRepository.save(member);
        return member;
    }

    public boolean isRegistrationBy(String oauthId) {
        Optional<Member> optionalMember = memberRepository.findByOauthId(oauthId);
        return optionalMember.isPresent();
    }

}
