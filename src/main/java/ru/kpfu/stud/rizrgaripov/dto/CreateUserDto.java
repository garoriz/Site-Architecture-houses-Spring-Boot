package ru.kpfu.stud.rizrgaripov.dto;

import javax.validation.constraints.NotBlank;

public class CreateUserDto {
    @NotBlank(message = "Имя не должно быть пустым!")
    private String name;

    @NotBlank(message = "Фамилия не должна быть пустой!")
    private String surname;

    @NotBlank(message = "Логин не должен быть пустым!")
    private String login;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private String password;

    private String confirmedPassword;

    public CreateUserDto() {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public CreateUserDto(String name, String surname, String login, String password, String confirmedPassword) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }
}
