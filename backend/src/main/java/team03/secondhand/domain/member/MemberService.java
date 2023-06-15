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
        // TODO: 리펙토링 필요 (위의 로직과 분리?)
        List<Long> locationIdList = requestJoinDto.getLocationIdList();
        for (Long locationId : locationIdList) {
            //TODO: 한번에 location 을 찾도록 수정
            //TODO: 로케이션을 찾지 못했을 경우는?
            Location location = locationRepository.findById(locationId).get();
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
