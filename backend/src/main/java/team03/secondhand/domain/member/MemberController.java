package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.member.dto.MemberDataRequestDto;
import team03.secondhand.domain.member.dto.MemberDataResponseDto;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public DataResponse<MemberDataResponseDto.Join> join(@RequestBody MemberDataRequestDto.Join requestJoinDto) {
        MemberDataResponseDto.Join memberDataJoin = memberService.join(requestJoinDto);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, memberDataJoin);
    }

    @GetMapping
    public DataResponse<MemberDataResponseDto.Info> show(@RequestAttribute Long memberId) {
        MemberDataResponseDto.Info memberDataInfo = memberService.getMemberById(memberId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, memberDataInfo);
    }

    @PatchMapping("/locations")
    public BaseResponse updateLocation(@RequestAttribute Long memberId , @RequestBody MemberDataRequestDto.UpdateLocation requestUpdateLocationDto) {
        memberService.updateLocations(memberId, requestUpdateLocationDto);
        return new BaseResponse(StatusCode.RESPONSE_SUCCESS);
    }

}
