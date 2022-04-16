package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

public class CreatePhotoDto {
    private String urlPhoto;
    private User user;

    public CreatePhotoDto() {
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreatePhotoDto(String urlPhoto, User user) {
        this.urlPhoto = urlPhoto;
        this.user = user;
    }
}
