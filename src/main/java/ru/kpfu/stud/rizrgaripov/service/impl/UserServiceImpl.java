package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.CreateUserDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;
import ru.kpfu.stud.rizrgaripov.model.User;
import ru.kpfu.stud.rizrgaripov.repository.UserRepository;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDto get(String login) {
        return userRepository.get(login).map(UserDto::fromModel).orElse(null);
    }

    @Override
    public UserDto signUp(CreateUserDto createUserDto, String url) {
        String encodedPassword = encoder.encode(createUserDto.getPassword());
        String urlPhoto = "<img src=https://res.cloudinary.com/dfn48aqa5/image/upload/v1634988875/" +
                "fb_avatar_ipvpno.jpg width=\"100\" height=\"111\">";
        User user = new User(
                createUserDto.getName(),
                createUserDto.getSurname(),
                " ",
                urlPhoto,
                createUserDto.getLogin(),
                encodedPassword
        );
        userRepository.save(user);
        return UserDto.fromModel(user);
    }

    @Override
    public UserDto get(int id) {
        return userRepository.getUserById(id).map(UserDto::fromModel).orElse(null);
    }

    @Override
    public void changeName(String login, String name) {
        userRepository.changeName(login, name);
    }

    @Override
    public void changeSurname(String login, String surname) {
        userRepository.changeSurname(login, surname);
    }

    @Override
    public void changeUrlPhoto(String login, String urlPhoto) {
        userRepository.changeUrlPhoto(login, urlPhoto);
    }

    @Override
    public void changeStatus(String login, String status) {
        userRepository.changeStatus(login, status);
    }

    @Override
    public void changePassword(String login, String password) {
        userRepository.changePassword(login, encoder.encode(password));
    }
}
