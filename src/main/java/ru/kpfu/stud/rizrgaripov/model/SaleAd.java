package ru.kpfu.stud.rizrgaripov.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class SaleAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 1, message = "Заголовок объявления не может быть пустым")
    @Column(nullable = false)
    private String heading;
    @Size(min = 1, message = "Текст объявления не может быть пустым")
    @Column(nullable = false)
    private String content;
    @Size(min = 1, message = "Значение цены не может быть пустым")
    @Column(nullable = false)
    private int price;
    @Size(min = 1, message = "Поле номера телефона не может быть пустым")
    @Column(nullable = false)
    private String phoneNumber;
    private String urlPhoto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public SaleAd() {
    }

    public SaleAd(String heading, String content, int price, String phoneNumber, String urlPhoto, User user) {
        this.heading = heading;
        this.content = content;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.urlPhoto = urlPhoto;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
