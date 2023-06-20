package team03.secondhand.domain.member;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import team03.secondhand.AuthorizationExtractor;
import team03.secondhand.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MemberInterceptor implements HandlerInterceptor {
    private AuthorizationExtractor authorizationExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public MemberInterceptor(AuthorizationExtractor authorizationExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = authorizationExtractor.extract(request, "Bearer");
        if (Strings.EMPTY.equals(token)) {
            return true;
        }
        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException();
        }

        Long memberId = jwtTokenProvider.getMemberId(token);
        request.setAttribute("memberId", memberId);
        return true;
    }
}
