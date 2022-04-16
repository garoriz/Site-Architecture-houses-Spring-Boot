package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.Message;
import ru.kpfu.stud.rizrgaripov.model.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

public class MessageDto {
    private int id;
    private String content;
    private User user;
    private User recipient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public MessageDto(int id, String content, User user, User recipient) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.recipient = recipient;
    }

    public static MessageDto fromModel(Message message) {
        return new MessageDto(
                message.getId(),
                message.getContent(),
                message.getUser(),
                message.getRecipient()
        );
    }
}
