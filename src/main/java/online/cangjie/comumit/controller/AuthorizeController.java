package online.cangjie.comumit.controller;

import online.cangjie.comumit.dto.AccessTokenDto;
import online.cangjie.comumit.dto.GithubUser;
import online.cangjie.comumit.interfaces.service.LoginService;
import online.cangjie.comumit.po.User;
import online.cangjie.comumit.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.url}")
    private String redirect_url;
    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/callback")
    public String callback(String code, String state, HttpSession session, HttpServletResponse response) {
        String accessToken = gitHubProvider.getAccessToken(new AccessTokenDto(client_id, client_secret, code, redirect_url, state));
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if (null != githubUser) {
            User user = loginService.login(githubUser);
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
