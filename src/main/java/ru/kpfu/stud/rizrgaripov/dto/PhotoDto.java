package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.Photo;
import ru.kpfu.stud.rizrgaripov.model.User;

public class PhotoDto {
    private int id;
    private String urlPhoto;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PhotoDto(int id, String urlPhoto, int userId) {
        this.id = id;
        this.urlPhoto = urlPhoto;
        this.userId = userId;
    }

    public static PhotoDto fromModel(Photo photo) {
        return new PhotoDto(
                photo.getId(),
                photo.getUrlPhoto(),
                photo.getUserId()
        );
    }
}
