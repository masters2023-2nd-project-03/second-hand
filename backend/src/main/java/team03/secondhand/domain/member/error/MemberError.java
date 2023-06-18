package team03.secondhand.domain.member.error;

import lombok.Data;

@Data
public class MemberError {

    @Data
    public static class DuplicatedUser extends RuntimeException {};

    @Data
    public static class TokenExpired extends RuntimeException {};

    @Data
    public static class RequireRegistration extends RuntimeException {};

    @Data
    public static class NotFoundLocation extends RuntimeException {};

    @Data
    public static class TokenIsNull extends RuntimeException {};

}
