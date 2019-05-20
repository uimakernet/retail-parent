package club.xyes.zkh.retail.web.front.interceptors.conf;

import club.xyes.zkh.retail.web.front.interceptors.ApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create by 郭文梁 2019/5/20 0020 19:03
 * InterceptorConfig
 * 拦截器配置
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final ApiInterceptor apiInterceptor;

    public InterceptorConfig(ApiInterceptor apiInterceptor) {
        this.apiInterceptor = apiInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor);
    }
}
