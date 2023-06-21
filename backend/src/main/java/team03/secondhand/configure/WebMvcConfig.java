package team03.secondhand.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team03.secondhand.domain.global.interceptor.MemberIdInterceptor;
import team03.secondhand.domain.global.interceptor.OAuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final OAuthInterceptor oAuthInterceptor;
    private final MemberIdInterceptor memberIdInterceptor;

    public WebMvcConfig(OAuthInterceptor oAuthInterceptor, MemberIdInterceptor memberIdInterceptor) {
        this.oAuthInterceptor = oAuthInterceptor;
        this.memberIdInterceptor = memberIdInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
        // 인증이 필요한 요청
        registry.addInterceptor(oAuthInterceptor)
                .addPathPatterns("/api/members", "/api/members/locations");
        // 인증이 필요하지 않은 요청
        registry.addInterceptor(memberIdInterceptor)
                .addPathPatterns("/api/products");
    }

}
