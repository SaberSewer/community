package online.cangjie.comumit.webconfig;

import online.cangjie.comumit.utils.CheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WeConfig implements WebMvcConfigurer {
    @Autowired
    private CheckLogin checkLogin;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor(checkLogin));
        registration.addPathPatterns("/**");                    //所有路径都被拦截
        registration.excludePathPatterns("/login","/error","/static/**","/logout");
    }
}
