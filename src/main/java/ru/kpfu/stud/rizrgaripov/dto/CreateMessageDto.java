package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

import javax.validation.constraints.NotBlank;

public class CreateMessageDto {
    @NotBlank(message = "Сообщение не должно быть пустым!")
    private String content;
    private int userId;
    private int recipientId;

    public CreateMessageDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CreateMessageDto(String content, int userId, int recipientId) {
        this.content = content;
        this.userId = userId;
        this.recipientId = recipientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }
}
