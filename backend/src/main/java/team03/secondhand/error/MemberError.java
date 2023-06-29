package team03.secondhand.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberError {

    @Getter
    @Setter
    public static class DuplicatedMember extends RuntimeException {
    }

    @Getter
    @Setter
    public static class TokenExpired extends RuntimeException {
    }

    @Getter
    @Setter
    public static class RequireRegistration extends RuntimeException {
    }

    @Getter
    @Setter
    public static class NotFoundLocation extends RuntimeException {
    }

    @Getter
    @Setter
    public static class TokenIsNull extends RuntimeException {
    }

    @Getter
    @Setter
    public static class InvalidGuest extends RuntimeException {
    }

}
