package xyz.mxue.printing.service.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.mxue.printing.service.interceptor.LoginInterceptor;
import xyz.mxue.printing.service.interceptor.PermissionInterceptor;


/**
 * @author mxuexxmy
 * @date 12/9/2020$ 11:41 PM$
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加自定义拦截器
     * .addPathPatterns("/**")  拦截的请求路径
     * .excludePathPatterns("/login"); 排除的请求路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/**", "/about");

        registry.addInterceptor(new PermissionInterceptor())
                .addPathPatterns("/**");
    }

}
