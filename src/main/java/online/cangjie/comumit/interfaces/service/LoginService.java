package online.cangjie.comumit.interfaces.service;

import online.cangjie.comumit.dto.GithubUser;
import online.cangjie.comumit.po.User;

public interface LoginService {
    User login(GithubUser githubUser);
}
