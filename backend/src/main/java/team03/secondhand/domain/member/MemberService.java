package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.location.LocationRepository;
import team03.secondhand.domain.member.dto.MemberDataRequestDto;
import team03.secondhand.domain.member.dto.MemberDataResponseDto;
import team03.secondhand.error.MemberError;
import team03.secondhand.util.imageUpload.ImageUploadModule;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final ImageUploadModule imageUploadModule;

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
        String profileUrl = imageUploadModule.profileImageUpload(requestJoinDto.getProfileUrl(), requestJoinDto.getOauthId());

        Member member = Member.builder()
                .nickname(requestJoinDto.getNickname())
                .profileUrl(profileUrl)
                .oauthId(requestJoinDto.getOauthId())
                .build();
        setLocations(member, "역삼1동"); // 최초 가입시 '역삼1동' 으로 설정
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

    private void setLocations(Member member, String searchKey) {
        Location foundLocations = locationRepository.findByLocationShortening(searchKey);
        member.deleteAllLocation();
        member.addLocation(foundLocations);
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
