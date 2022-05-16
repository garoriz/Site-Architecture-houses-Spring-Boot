package ru.kpfu.stud.rizrgaripov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.stud.rizrgaripov.dto.MessageDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;
import ru.kpfu.stud.rizrgaripov.service.UserService;

@Controller
public class MessageController {

    private final UserService userService;
    private String nameAndSurname = "";

    @Autowired
    public MessageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/chat")
    public String getChatPage() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserDto userDto = userService.get(username);
        nameAndSurname = userDto.getName() + " " + userDto.getSurname() + ": ";
        return "chat";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String message(MessageDto messageDto) {
        return nameAndSurname + messageDto.getContent();
    }
}
