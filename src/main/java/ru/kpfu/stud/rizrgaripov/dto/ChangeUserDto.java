package ru.kpfu.stud.rizrgaripov.dto;

import javax.validation.constraints.NotBlank;

public class ChangeUserDto {
    @NotBlank(message = "Имя не должно быть пустым!")
    private String name;

    @NotBlank(message = "Фамилия не должна быть пустой!")
    private String surname;

    @NotBlank(message = "Пароль не должен быть пустым!")
    private String password;

    private String confirmedPassword;

    private String status;

    public ChangeUserDto() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChangeUserDto(String name, String surname, String password, String confirmedPassword, String status) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.status = status;
    }
}
