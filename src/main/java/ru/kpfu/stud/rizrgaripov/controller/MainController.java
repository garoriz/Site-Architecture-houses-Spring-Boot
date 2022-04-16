package ru.kpfu.stud.rizrgaripov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.stud.rizrgaripov.dto.CreateArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateUserDto;

@Controller
public class MainController {
    @GetMapping("/registration")
    public String getSignUp(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "registration";
    }

    @GetMapping("/addArticle")
    public String getAddArticle(Model model) {
        model.addAttribute("article", new CreateArticleDto());
        return "add_article";
    }
}
