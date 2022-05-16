package ru.kpfu.stud.rizrgaripov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.stud.rizrgaripov.dto.*;
import ru.kpfu.stud.rizrgaripov.service.ArticleService;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
public class ArticleRestController {
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleRestController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/articleRest/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable(name = "id") int id, HttpServletRequest request) {
        ArticleDto articleDto = articleService.get(id);
        UserDto userDto = userService.get(articleDto.getUserId());
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    @GetMapping("/articlesRest")
    public ResponseEntity<List<ArticleDto>> getArticles() {
        List<ArticleDto> articlesDto = articleService.getAll();
        Collections.reverse(articlesDto);
        return new ResponseEntity<>(articlesDto, HttpStatus.OK);
    }

    @GetMapping("/myArticlesRest")
    public ResponseEntity<List<ArticleDto>> getMyArticles() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserDto user = userService.get(username);
        int id = user.getId();
        List<ArticleDto> articlesDto = articleService.getAllByUserId(id);
        Collections.reverse(articlesDto);
        return new ResponseEntity<>(articlesDto, HttpStatus.OK);
    }
}
