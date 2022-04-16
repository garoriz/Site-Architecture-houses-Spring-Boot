package ru.kpfu.stud.rizrgaripov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.stud.rizrgaripov.dto.CreateUserDto;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        request.getSession().setAttribute("login", userDto.getLogin());
        return "sign_up_success";
    }
}
