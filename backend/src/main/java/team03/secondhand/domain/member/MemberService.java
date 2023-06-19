package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.location.LocationRepository;
import team03.secondhand.domain.member.dto.MemberDataRequestDto;
import team03.secondhand.domain.member.dto.MemberDataResponseDto;
import team03.secondhand.domain.member.error.MemberError;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    private final JwtTokenProvider jwtTokenProvider;

    /*
    /**********************************************************************
    /* Public Method
    /**********************************************************************
     */

    public MemberDataResponseDto.Info getMemberById(Long id) {
        Member member = memberRepository.findByMemberId(id)
                .orElseThrow(MemberError.RequireRegistration::new);
        return new MemberDataResponseDto.Info(member);
    }

    @Transactional
    public MemberDataResponseDto.Join join(MemberDataRequestDto.Join requestJoinDto) {
        isRegistrationBy(requestJoinDto.getOauthId());

        Member member = Member.builder()
                .nickname(requestJoinDto.getNickname())
                .profileUrl(requestJoinDto.getProfileUrl())
                .oauthId(requestJoinDto.getOauthId())
                .build();
        updateLocations(member, requestJoinDto.getLocationIdList());
        memberRepository.save(member);
        return new MemberDataResponseDto.Join(member, createToken(member));
    }

    @Transactional
    public void updateLocations(Long memberId, MemberDataRequestDto.UpdateLocation requestUpdateLocationDto) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(MemberError.RequireRegistration::new);
        updateLocations(member, requestUpdateLocationDto.getLocationIdList());
    }

    /*
    /**********************************************************************
    /* Private Method
    /**********************************************************************
     */

    private void updateLocations(Member member, List<Long> locationIdList) {
        List<Location> foundLocations = locationRepository.findAllByLocationIdIn(locationIdList);
        if (foundLocations.size() != locationIdList.size()) {
            throw new MemberError.NotFoundLocation();
        }
        member.deleteAllLocation();
        foundLocations.forEach(member::addLocation);
    }

    private void isRegistrationBy(String oauthId) {
        if (memberRepository.existsByOauthId(oauthId)) {
            throw new MemberError.DuplicatedMember();
        }
    }

    private String createToken(Member savedMember) {
        String memberId = String.valueOf(savedMember.getMemberId());
        return jwtTokenProvider.createToken(memberId);
    }

}
