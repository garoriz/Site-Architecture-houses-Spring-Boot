package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.User;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class ArticleDto {
    private int id;
    private String heading;
    private String content;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleDto(int id, String heading, String content, User user) {
        this.id = id;
        this.heading = heading;
        this.content = content;
        this.user = user;
    }

    public static ArticleDto fromModel(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getHeading(),
                article.getContent(),
                article.getUser()
        );
    }
}