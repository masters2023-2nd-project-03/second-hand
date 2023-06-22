package team03.secondhand.domain.member.error;

import lombok.Getter;
import lombok.Setter;
import team03.secondhand.domain.StatusCode;

@Getter @Setter
public class MemberError {

    @Getter @Setter
    public static class DuplicatedMember extends RuntimeException {};

    @Getter @Setter
    public static class TokenExpired extends RuntimeException {};

    @Getter @Setter
    public static class RequireRegistration extends RuntimeException {};

    @Getter @Setter
    public static class NotFoundLocation extends RuntimeException {};

    @Getter @Setter
    public static class TokenIsNull extends RuntimeException {};

    @Getter @Setter
    public static class InvalidGuest extends RuntimeException {};

}
