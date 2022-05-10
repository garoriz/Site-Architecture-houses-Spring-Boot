package ru.kpfu.stud.rizrgaripov.dto;

import javax.validation.constraints.NotBlank;

public class ChangeSaleAdDto {
    @NotBlank(message = "Заголовок не должен быть пустым!")
    private String heading;
    @NotBlank(message = "Текст объявления не должен быть пустым!")
    private String content;
    @NotBlank(message = "Цена не должна быть пустой!")
    private int price;
    @NotBlank(message = "Номер телефона не должен быть пустым!")
    private String phoneNumber;
    private String urlPhoto;
    private int id;

    public ChangeSaleAdDto() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChangeSaleAdDto(String heading, String content, int price, String phoneNumber, String urlPhoto, int id) {
        this.heading = heading;
        this.content = content;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.urlPhoto = urlPhoto;
        this.id = id;
    }
}
