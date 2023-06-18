package team03.secondhand.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.member.dto.MemberDataRequest;
import team03.secondhand.domain.member.dto.MemberDataResponse;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public DataResponse<MemberDataResponse.Join> join(@RequestBody MemberDataRequest.Join requestJoinDto) {
        MemberDataResponse.Join memberDataJoin = memberService.join(requestJoinDto);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, memberDataJoin);
    }

    @GetMapping
    public DataResponse<MemberDataResponse.Info> show(@RequestAttribute Long memberId) {
        MemberDataResponse.Info memberDataInfo = memberService.getMemberById(memberId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, memberDataInfo);
    }

    @PatchMapping("/locations")
    public BaseResponse updateLocation(@RequestAttribute Long memberId , @RequestBody MemberDataRequest.UpdateLocation requestUpdateLocationDto) {
        memberService.updateLocations(memberId, requestUpdateLocationDto);
        return new BaseResponse(StatusCode.RESPONSE_SUCCESS);
    }

}
