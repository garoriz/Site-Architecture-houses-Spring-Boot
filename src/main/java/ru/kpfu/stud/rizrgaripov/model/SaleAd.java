package ru.kpfu.stud.rizrgaripov.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class SaleAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    @Size(min = 1, message = "Заголовок объявления не может быть пустым")
    @Column(nullable = false)
    private String heading;
    @Size(min = 1, message = "Текст объявления не может быть пустым")
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int price;
    @Size(min = 1, message = "Поле номера телефона не может быть пустым")
    @Column(nullable = false)
    private String phoneNumber;
    private String urlPhoto;

    public SaleAd() {
    }

    public SaleAd(int userId, String heading, String content, int price, String phoneNumber, String urlPhoto) {
        this.userId = userId;
        this.heading = heading;
        this.content = content;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.urlPhoto = urlPhoto;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
