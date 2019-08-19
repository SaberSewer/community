package online.cangjie.comumit.controller;

import online.cangjie.comumit.interfaces.service.ProfileService;
import online.cangjie.comumit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile/")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("{action}")
    public String profile(@PathVariable(name = "action") String action, Model model, Integer pageNo, HttpSession session) {
        User user = (User) session.getAttribute("user");
        switch (action) {
            case "questions":
                model.addAttribute("question", profileService.myQuestion(user.getId(), pageNo));
                model.addAttribute("title", "我的问题");
                return "profile";
            case "myInfo":
                model.addAttribute("title", "我的信息");
                return "profile-myinfo";
        }
        return "profile";
    }
}
