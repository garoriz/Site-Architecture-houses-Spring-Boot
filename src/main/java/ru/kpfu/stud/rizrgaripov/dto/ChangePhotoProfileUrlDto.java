package ru.kpfu.stud.rizrgaripov.dto;

public class ChangePhotoProfileUrlDto {
    private String photoUrl;

    public ChangePhotoProfileUrlDto() {
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ChangePhotoProfileUrlDto(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
