package ru.kpfu.stud.rizrgaripov.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 1, message = "Заголовок статьи не может быть пустым")
    @Column(nullable = false)
    private String heading;
    @Size(min = 1, message = "Текст статьи не может быть пустым")
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Article() {
    }

    public Article(String heading, String content, User user) {
        this.heading = heading;
        this.content = content;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
