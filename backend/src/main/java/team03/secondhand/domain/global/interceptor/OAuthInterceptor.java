package team03.secondhand.domain.global.interceptor;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import team03.secondhand.AuthorizationExtractor;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.error.MemberError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class OAuthInterceptor implements HandlerInterceptor {
    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Preflight인 경우 허용
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        String token = authorizationExtractor.extract(request, "Bearer");

        // 개발용 토큰
        if (token.equals("admin")) {
            request.setAttribute("memberId", 1L);
            return true;
        }

        if (Strings.EMPTY.equals(token)) {
            throw new MemberError.TokenIsNull();
        }
        if (!jwtTokenProvider.validateToken(token)) {
            throw new MemberError.TokenExpired();
        }

        Long memberId = jwtTokenProvider.getMemberId(token);
        request.setAttribute("memberId", memberId);
        return true;
    }
}
