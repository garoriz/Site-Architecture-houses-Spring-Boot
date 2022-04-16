package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.ArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateArticleDto;
import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.repository.ArticleRepository;
import ru.kpfu.stud.rizrgaripov.repository.UserRepository;
import ru.kpfu.stud.rizrgaripov.service.ArticleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository.findAll().stream().map(ArticleDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public ArticleDto get(int id) {
        return articleRepository.findById(id).map(ArticleDto::fromModel).orElse(null);
    }

    @Override
    public void save(CreateArticleDto createArticleDto) {
        Article article = new Article(
                createArticleDto.getHeading(),
                createArticleDto.getContent(),
                createArticleDto.getUser()
        );
        articleRepository.save(article);
    }
}
