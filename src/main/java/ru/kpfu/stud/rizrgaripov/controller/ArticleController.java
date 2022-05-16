package ru.kpfu.stud.rizrgaripov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.stud.rizrgaripov.dto.ArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;
import ru.kpfu.stud.rizrgaripov.service.ArticleService;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

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
    public String addArticle(@ModelAttribute(name = "article") CreateArticleDto articleDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        articleDto.setUserId(userService.get(username).getId());
        articleService.save(articleDto);
        return "article_added";
    }

    @GetMapping("/article")
    public String getArticle(Model modelArticle, Model modelUser, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ArticleDto articleDto = articleService.get(id);
        UserDto userDto = userService.get(articleDto.getUserId());
        modelArticle.addAttribute("article", articleDto);
        modelUser.addAttribute("user", userDto);
        return "article";
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleDto> articlesDto = articleService.getAll();
        Collections.reverse(articlesDto);
        model.addAttribute("articles", articlesDto);
        return "articles";
    }

    @GetMapping("/myArticles")
    public String getMyArticles(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserDto user = userService.get(username);
        int id = user.getId();
        List<ArticleDto> articlesDto = articleService.getAllByUserId(id);
        Collections.reverse(articlesDto);
        model.addAttribute("articles", articlesDto);
        return "my_articles";
    }
}
