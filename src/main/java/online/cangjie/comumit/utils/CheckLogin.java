package online.cangjie.comumit.utils;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.comumit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
@Component("checkLogin")
public class CheckLogin {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void checkLoginByCookies(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        if(cookies == null )
            return;
        for (Cookie cookie : cookies) {
            if ("user_session".equals(cookie.getName())) {
                JSONObject json = (JSONObject) JSONObject.parse(stringRedisTemplate.opsForValue().get("user_session:" + cookie.getValue()));
                if (json == null) {
                    break;
                }
                User user = JSONUtil.jsonToObject(new User(), json);
                request.getSession().setAttribute("user", user);
                stringRedisTemplate.expire("user_session:" + cookie.getValue(), 3600, TimeUnit.SECONDS);
                break;
            }
        }
    }
}
