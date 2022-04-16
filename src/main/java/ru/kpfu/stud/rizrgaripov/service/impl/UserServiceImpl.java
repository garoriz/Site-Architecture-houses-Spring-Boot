package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.CreateUserDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;
import ru.kpfu.stud.rizrgaripov.model.User;
import ru.kpfu.stud.rizrgaripov.repository.UserRepository;
import ru.kpfu.stud.rizrgaripov.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final String URL_PHOTO = "<img src=https://res.cloudinary.com/dfn48aqa5/image/upload/v1634988875/" +
            "fb_avatar_ipvpno.jpg width=\"100\" height=\"111\">";
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
        User user = new User(
                createUserDto.getName(),
                createUserDto.getSurname(),
                " ",
                URL_PHOTO,
                createUserDto.getLogin(),
                createUserDto.getPassword());
        userRepository.save(user);
        return UserDto.fromModel(user);
    }

    @Override
    public UserDto get(int id) {
        return userRepository.getUserById(id).map(UserDto::fromModel).orElse(null);
    }
}
