package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.location.LocationRepository;
import team03.secondhand.domain.member.dto.MemberDataRequest;
import team03.secondhand.domain.member.dto.MemberDataResponse;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public MemberDataResponse.Info getMemberById(Long id) {
        Member member = memberRepository.findByMemberId(id)
                .orElseThrow(NoSuchElementException::new);
        return new MemberDataResponse.Info(member);
    }

    @Transactional
    public MemberDataResponse.Join join(MemberDataRequest.Join  requestJoinDto) {
        isRegistrationBy(requestJoinDto.getOauthId());

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
            member.addLocation(memberAndLocation);
        }
        memberRepository.save(member);
        return new MemberDataResponse.Join(member, createToken(member));
    }


    private void isRegistrationBy(String oauthId) {
        if (memberRepository.findByOauthId(oauthId).isPresent()) {
            throw new DataIntegrityViolationException("");
        }
    }

    private String createToken(Member savedMember) {
        String memberId = String.valueOf(savedMember.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
