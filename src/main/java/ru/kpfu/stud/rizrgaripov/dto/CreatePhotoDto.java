package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

public class CreatePhotoDto {
    private String urlPhoto;
    private int userId;

    public CreatePhotoDto() {
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CreatePhotoDto(String urlPhoto, int userId) {
        this.urlPhoto = urlPhoto;
        this.userId = userId;
    }
}
