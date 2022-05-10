package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

import javax.validation.constraints.NotBlank;

public class CreateArticleDto {
    @NotBlank(message = "Заголовок не должен быть пустым!")
    private String heading;
    @NotBlank(message = "Текст статьи не должен быть пустым!")
    private String content;
    private int userId;

    public CreateArticleDto() {
    }

    public CreateArticleDto(String heading, String content, int userId) {
        this.heading = heading;
        this.content = content;
        this.userId = userId;
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

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
