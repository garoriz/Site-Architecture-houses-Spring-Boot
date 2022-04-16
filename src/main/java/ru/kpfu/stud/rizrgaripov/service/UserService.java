package ru.kpfu.stud.rizrgaripov.service;

import ru.kpfu.stud.rizrgaripov.dto.CreateUserDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;

public interface UserService {
    UserDto get(String login);

    UserDto signUp(CreateUserDto createUserDto, String url);

    UserDto get(int id);
}
