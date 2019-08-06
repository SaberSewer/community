package online.cangjie.comumit.controller;

import online.cangjie.comumit.interfaces.service.QuestionService;
import online.cangjie.comumit.po.Question;
import online.cangjie.comumit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @RequestMapping("/publish/create")
    public String create(HttpSession session, Question question){
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        question.setCreator(user.getId());
        questionService.addQuestionService(question);
        return "redirect:/";
    }
}
