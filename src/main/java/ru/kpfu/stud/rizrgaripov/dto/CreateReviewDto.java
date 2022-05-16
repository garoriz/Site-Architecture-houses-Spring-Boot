package ru.kpfu.stud.rizrgaripov.dto;

import javax.validation.constraints.NotBlank;

public class CreateReviewDto {
    @NotBlank(message = "Имя автора не должно быть пустым")
    private String authorName;
    private int rating;
    @NotBlank(message = "Текст отзыва не может быть пустым")
    private String content;

    public CreateReviewDto() {
    }

    public CreateReviewDto(String authorName, int rating, String content) {
        this.authorName = authorName;
        this.rating = rating;
        this.content = content;
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
