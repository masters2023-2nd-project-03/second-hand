package team03.secondhand.oauth2.error;

import team03.secondhand.oauth2.dto.MemberDto;


public class RequireRegistrationError extends RuntimeException {

    private MemberDto memberDto;

    public RequireRegistrationError(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

}
