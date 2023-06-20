package team03.secondhand.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team03.secondhand.domain.member.MemberInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final MemberInterceptor memberInterceptor;

    public WebMvcConfig(MemberInterceptor memberInterceptor) {
        this.memberInterceptor = memberInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(memberInterceptor).addPathPatterns("/api/members");
    }

}
