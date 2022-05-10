package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.User;

public class ArticleDto {
    private int id;
    private String heading;
    private String content;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleDto(int id, String heading, String content, int userId) {
        this.id = id;
        this.heading = heading;
        this.content = content;
        this.userId = userId;
    }

    public static ArticleDto fromModel(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getHeading(),
                article.getContent(),
                article.getUserId()
        );
    }
}
