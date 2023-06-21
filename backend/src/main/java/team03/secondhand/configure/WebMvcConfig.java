package team03.secondhand.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team03.secondhand.domain.member.MemberInterceptor;
import team03.secondhand.domain.product.ProductInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final MemberInterceptor memberInterceptor;
    private final ProductInterceptor productInterceptor;

    public WebMvcConfig(MemberInterceptor memberInterceptor, ProductInterceptor productInterceptor) {
        this.memberInterceptor = memberInterceptor;
        this.productInterceptor = productInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/api/members", "/api/members/locations");
        registry.addInterceptor(productInterceptor)
                .addPathPatterns("/api/products");
    }

}
