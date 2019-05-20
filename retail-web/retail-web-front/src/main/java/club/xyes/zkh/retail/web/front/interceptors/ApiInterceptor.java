package club.xyes.zkh.retail.web.front.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by 郭文梁 2019/5/20 0020 19:01
 * ApiInterceptor
 * Api数据访问拦截器 配置跨域
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
        return true;
    }
}
