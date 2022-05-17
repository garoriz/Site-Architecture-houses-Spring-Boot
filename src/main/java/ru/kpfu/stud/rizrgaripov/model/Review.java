package ru.kpfu.stud.rizrgaripov.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class  Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 1, message = "Имя автора не должно быть пустым")
    @Column(nullable = false)
    private String authorName;
    private int rating;
    @Size(min = 1, message = "Текст отзыва не может быть пустым")
    @Column(nullable = false)
    private String content;

    public Review() {
    }

    public Review(String authorName, int rating, String content) {
        this.authorName = authorName;
        this.rating = rating;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
