package ru.kpfu.stud.rizrgaripov.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.stud.rizrgaripov.dto.*;
import ru.kpfu.stud.rizrgaripov.model.User;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;

@Controller
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class UserController {
    private static final int DIRECTORIES_COUNT = 100;
    private static final String FILE_PATH_PREFIX = "/tmp";
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dfn48aqa5",
            "api_key", "591695994741495",
            "api_secret", "fWcdsdRM6uR3o5NdZDGAzVli5bo"));
    private static final Logger logger
            = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto, HttpServletRequest request) {
        String name = userDto.getName();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String confirmedPassword = userDto.getConfirmedPassword();
        if ((userService.get(login)) != null || name.trim().equals("")) {
            return "registration";
        } else if (password.length() > 7 && password.equals(confirmedPassword)) {
            String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
            userService.signUp(userDto, url);
            return "main_page";
        } else {
            return "registration";
        }
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpServletRequest request) {
        model.addAttribute("user", new UserDto());
        String urlParam = request.getParameter("login");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        if (urlParam == null && username != null) {
            UserDto user = userService.get(username);
            model.addAttribute("user", user);
            return "profile";
        } else if (username != null && username.equals(urlParam)) {
            UserDto user = userService.get(username);
            model.addAttribute("user", user);
            return "profile";
        } else if (username != null && !username.equals(urlParam)) {
            UserDto user = userService.get(urlParam);
            model.addAttribute("user", user);
            return "other_user_profile";
        } else if (urlParam != null && userService.get(urlParam) != null) {
            UserDto user = userService.get(urlParam);
            model.addAttribute("user", user);
            return "other_user_profile";
        } else if (urlParam == null) {
            return "signIn";
        }
        return "profile";
    }

    @PostMapping("/settings")
    public String postSettings(@ModelAttribute(name = "user") ChangeUserDto userDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        String name = userDto.getName();
        String surname = userDto.getSurname();
        String status = userDto.getStatus();
        String password = userDto.getPassword();
        String confirmedPassword = userDto.getConfirmedPassword();
        if (!name.equals("")) {
            userService.changeName(username, name);
        }
        if (!surname.equals("")) {
            userService.changeSurname(username, surname);
        }
        if (!status.equals("")) {
            userService.changeStatus(username, status);
        }
        if (!password.equals("") | !confirmedPassword.equals("")) {
            if (password.equals(confirmedPassword)) {
                userService.changePassword(username, password);
            }
        }
        return "settings";
    }

    @PostMapping("/uploadPhotoProfile")
    public String uploadPhotoProfile(
            @ModelAttribute(name = "user") ChangePhotoProfileUrlDto photoDto,
            HttpServletRequest request
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        Part part = null;
        try {
            part = request.getPart("file");
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage());
        }
        if (part.getSize() == 0) {
            return "upload_photo_profile";
        } else {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            InputStream content = null;
            try {
                content = part.getInputStream();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            int partNumber = Math.abs(fileName.hashCode() % DIRECTORIES_COUNT);
            File file = new File(FILE_PATH_PREFIX + File.separator + partNumber + File.separator + fileName);
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
            }
            byte[] buffer = new byte[0];
            try {
                buffer = new byte[content.available()];
                content.read(buffer);
                outputStream.write(buffer);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            String publicId = username + new Date().getTime();
            try {
                cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", publicId));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            userService.changeUrlPhoto(username, cloudinary.url().transformation(new Transformation().width(100)
                    .height(111)).imageTag(publicId));

            return "upload_photo_profile";
        }
    }
}
