package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

import javax.validation.constraints.NotBlank;

public class CreateMessageDto {
    @NotBlank(message = "Сообщение не должно быть пустым!")
    private String content;
    private User user;
    private User recipient;

    public CreateMessageDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CreateMessageDto(String content, User user, User recipient) {
        this.content = content;
        this.user = user;
        this.recipient = recipient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
