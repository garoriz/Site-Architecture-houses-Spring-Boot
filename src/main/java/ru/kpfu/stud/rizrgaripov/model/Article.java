package ru.kpfu.stud.rizrgaripov.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    @Size(min = 1, message = "Заголовок статьи не может быть пустым")
    @Column(nullable = false)
    private String heading;
    @Size(min = 1, message = "Текст статьи не может быть пустым")
    @Column(nullable = false)
    private String content;

    public Article() {
    }

    public Article(int userId, String heading, String content) {
        this.userId = userId;
        this.heading = heading;
        this.content = content;
    }

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
