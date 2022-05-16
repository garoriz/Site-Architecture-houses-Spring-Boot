package ru.kpfu.stud.rizrgaripov.service;

import org.springframework.data.jpa.repository.Query;
import ru.kpfu.stud.rizrgaripov.dto.CreateUserDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;
import ru.kpfu.stud.rizrgaripov.model.User;

public interface UserService {
    UserDto get(String login);

    UserDto signUp(CreateUserDto createUserDto, String url);

    UserDto get(int id);

    void changeName(String login, String name);

    void changeSurname(String login, String surname);

    void changeUrlPhoto(String login, String urlPhoto);

    void changeStatus(String login, String status);

    void changePassword(String login, String password);
}
