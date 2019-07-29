package online.cangjie.comumit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InedxController {
    @GetMapping("/")
    public String test(String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }
}
