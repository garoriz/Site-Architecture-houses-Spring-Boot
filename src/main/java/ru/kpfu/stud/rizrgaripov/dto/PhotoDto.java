package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.Photo;
import ru.kpfu.stud.rizrgaripov.model.User;

import javax.persistence.Column;

public class PhotoDto {
    private int id;
    private String urlPhoto;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PhotoDto(int id, String urlPhoto, User user) {
        this.id = id;
        this.urlPhoto = urlPhoto;
        this.user = user;
    }

    public static PhotoDto fromModel(Photo photo) {
        return new PhotoDto(
                photo.getId(),
                photo.getUrlPhoto(),
                photo.getUser()
        );
    }
}
