package online.cangjie.comumit.controller;

import online.cangjie.comumit.interfaces.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InedxController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String index(Model model, Integer pageNo){
        model.addAttribute("hot_title", indexService.getHotCat());
        model.addAttribute("question", indexService.getAllQuestion(pageNo));
        return "index";
    }

    @PostMapping("/searcher")
    public String searcher(String message, Model model, Integer pageNo){
        model.addAttribute("hot_title", indexService.getHotCat());
        model.addAttribute("question", indexService.getQuestionBy(message, pageNo));
        return "index";
    }
}
