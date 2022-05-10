package ru.kpfu.stud.rizrgaripov.service;

import ru.kpfu.stud.rizrgaripov.dto.ArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateArticleDto;
import ru.kpfu.stud.rizrgaripov.model.Article;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getAll();

    List<ArticleDto> getAllByUserId(int id);

    ArticleDto get(int id);

    void save(CreateArticleDto createArticleDto);
}
