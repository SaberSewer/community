package online.cangjie.comumit.webconfig;

import online.cangjie.comumit.utils.CheckLogin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
    private CheckLogin checkLogin;

    public LoginInterceptor(CheckLogin checkLogin){
        this.checkLogin = checkLogin;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {       //请求进入这个拦截器
        checkLogin.checkLoginByCookies(request, response);
        return true;        //有的话就继续操作
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
