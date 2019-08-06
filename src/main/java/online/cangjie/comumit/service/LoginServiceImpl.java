package online.cangjie.comumit.service;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.comumit.dao.UserDao;
import online.cangjie.comumit.dto.GithubUser;
import online.cangjie.comumit.interfaces.service.LoginService;
import online.cangjie.comumit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public User login(GithubUser githubUser){
        //用户是否已经注册
        User user = userDao.queryById(String.valueOf(githubUser.getId()));
        System.out.println(user);
        if (user != null) {
            String name = stringRedisTemplate.opsForValue().get("user_session:"+user.getToken());
            if (name == null){
                String token = UUID.randomUUID().toString().replace("-", "");
                userDao.updateTokenByAccess_id(token, String.valueOf(githubUser.getId()));
                addRedis(user);
            }
        }else{
            user = new User();
            user.setAccess_id(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString().replace("-", "");
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_ified(user.getGmt_create());
            user.setBio(githubUser.getBio());
            user.setAvatar_url(githubUser.getAvatar_url());
            if(1 == userDao.insertUser(user)){
                addRedis(user);
            }
        }
        return user;
    }

    private void addRedis(User user){
        stringRedisTemplate.opsForValue().set("user_session:" + user.getToken(), JSONObject.toJSONString(user));
        stringRedisTemplate.expire("user_session:" + user.getToken(), 3600, TimeUnit.SECONDS);
    }
}
