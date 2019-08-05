package online.cangjie.comumit.controller;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.comumit.utils.JSONUtil;
import online.cangjie.comumit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class InedxController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("/")
    public String index(HttpSession session, HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        for ( Cookie cookie : cookies) {
            if(cookie.getName().equals("user_session")){
                JSONObject json = (JSONObject) JSONObject.parse(stringRedisTemplate.opsForValue().get("user_session:" + cookie.getValue()));
                User user = JSONUtil.jsonToObject(new User(), json);
                session.setAttribute("user", user);
                break;
            }
        }
        return "index";
    }
}
