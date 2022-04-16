package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.SaleAd;
import ru.kpfu.stud.rizrgaripov.model.User;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class SaleAdDto {
    private int id;
    private String heading;
    private String content;
    private int price;
    private String phoneNumber;
    private String urlPhoto;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public SaleAdDto(
            int id,
            String heading,
            String content,
            int price,
            String phoneNumber,
            String urlPhoto,
            User user
    ) {
        this.id = id;
        this.heading = heading;
        this.content = content;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.urlPhoto = urlPhoto;
        this.user = user;
    }

    public static SaleAdDto fromModel(SaleAd saleAd) {
        return new SaleAdDto(
                saleAd.getId(),
                saleAd.getHeading(),
                saleAd.getContent(),
                saleAd.getPrice(),
                saleAd.getPhoneNumber(),
                saleAd.getUrlPhoto(),
                saleAd.getUser()
        );
    }
}
