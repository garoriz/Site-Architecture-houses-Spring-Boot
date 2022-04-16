package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateArticleDto {
    @NotBlank(message = "Заголовок не должен быть пустым!")
    private String heading;
    @NotBlank(message = "Текст статьи не должен быть пустым!")
    private String content;
    private User user;

    public CreateArticleDto() {
    }

    public CreateArticleDto(String heading, String content, User user) {
        this.heading = heading;
        this.content = content;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
