package ru.kpfu.stud.rizrgaripov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.stud.rizrgaripov.dto.CreateArticleDto;
import ru.kpfu.stud.rizrgaripov.service.ArticleService;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute(name = "article") CreateArticleDto articleDto, HttpServletRequest request) {
        //articleDto.setUser(userService.get((String) request.getSession().getAttribute("login")));
        articleService.save(articleDto);
        return "sign_up_success";
    }
}
