package online.cangjie.comumit.service;

import online.cangjie.comumit.dao.UserDao;
import online.cangjie.comumit.dto.GithubUser;
import online.cangjie.comumit.interfaces.service.LoginService;
import online.cangjie.comumit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    public User login(GithubUser githubUser){
        User user = userDao.queryById(String.valueOf(githubUser.getId()));
        if (user != null) {
            return user;
        }
        user = new User();
        user.setAccess_id(String.valueOf(githubUser.getId()));
        user.setName(githubUser.getName());
        user.setToken(UUID.randomUUID().toString().replace("-", ""));
        user.setGmt_create(System.currentTimeMillis());
        user.setGmt_ified(user.getGmt_create());
        if(1 == userDao.insertUser(user)){
            return user;
        }

        return null;
    }
}
