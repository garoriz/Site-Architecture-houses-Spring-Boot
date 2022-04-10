package ru.kpfu.stud.rizrgaripov.dto;

import ru.kpfu.stud.rizrgaripov.model.User;

public class UserDto {
    private Integer id;

    private String name;

    private String surname;

    private String status;

    private String urlPhoto;

    private String login;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserDto(Integer id, String name, String surname, String status, String urlPhoto, String login) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.urlPhoto = urlPhoto;
        this.login = login;
    }

    public static UserDto fromModel(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getStatus(),
                user.getUrlPhoto(),
                user.getLogin()
        );
    }
}
