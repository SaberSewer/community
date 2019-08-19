package online.cangjie.comumit.controller;

import online.cangjie.comumit.interfaces.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "question")
    public String showQuestion(Model model, Integer id){
        model.addAttribute("question", questionService.getQuestionById(id));
        return "question-info";
    }
}
