package team03.secondhand.domain.member;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.location.LocationRepository;
import team03.secondhand.domain.member.dto.MemberDataRequestDto;
import team03.secondhand.domain.member.dto.MemberDataResponseDto;
import team03.secondhand.domain.member.error.MemberError;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${aws.bucketName}")
    private String S3Bucket; // Bucket 이름
    @Value("${aws.bucketProfileFolderPath}")
    private String folderPath; // 폴더 경로
    @Autowired
    AmazonS3Client amazonS3Client;

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
                .profileUrl(uploadProfileImage(requestJoinDto.getProfileUrl(), requestJoinDto.getOauthId()))
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

    private String uploadProfileImage(MultipartFile multipartFile, String oauthId) {
        String imgName = "profile_" + oauthId; // 파일 이름(멤버ID)
        long size = multipartFile.getSize(); // 파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);

        // S3에 업로드
        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, folderPath + imgName, multipartFile.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (Exception e) {
            log.error(("예외 발생: " + e.getMessage()));
        }
        return amazonS3Client.getUrl(S3Bucket, folderPath + imgName).toString(); // 접근가능한 URL 가져오기
    }

}
