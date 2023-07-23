package team03.secondhand.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team03.secondhand.domain.global.interceptor.MemberIdInterceptor;
import team03.secondhand.domain.global.interceptor.OAuthInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final OAuthInterceptor oAuthInterceptor;
    private final MemberIdInterceptor memberIdInterceptor;

    public WebMvcConfiguration(OAuthInterceptor oAuthInterceptor, MemberIdInterceptor memberIdInterceptor) {
        this.oAuthInterceptor = oAuthInterceptor;
        this.memberIdInterceptor = memberIdInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // 인가가 필요
        registry.addInterceptor(memberIdInterceptor)
                .addPathPatterns("/api/members", "/api/members/locations", "/api/watchlist", "/api/products", "/api/products/*");
        // 인증이 필요
        registry.addInterceptor(oAuthInterceptor)
                .addPathPatterns("/chat/rooms", "/chat/room/create");
    }

}
