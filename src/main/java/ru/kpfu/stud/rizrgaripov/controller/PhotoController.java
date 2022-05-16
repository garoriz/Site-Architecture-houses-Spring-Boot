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
import ru.kpfu.stud.rizrgaripov.dto.CreatePhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.PhotoDto;
import ru.kpfu.stud.rizrgaripov.dto.UserDto;
import ru.kpfu.stud.rizrgaripov.service.PhotoService;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class PhotoController {
    private static final int DIRECTORIES_COUNT = 100;
    private static final String FILE_PATH_PREFIX = "/tmp";
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dfn48aqa5",
            "api_key", "591695994741495",
            "api_secret", "fWcdsdRM6uR3o5NdZDGAzVli5bo"));
    private static final Logger logger
            = LoggerFactory.getLogger(PhotoController.class);

    private final PhotoService photoService;
    private final UserService userService;

    @Autowired
    public PhotoController(PhotoService photoService, UserService userService) {
        this.photoService = photoService;
        this.userService = userService;
    }

    @PostMapping("/addPhoto")
    public String addPhoto(
            @ModelAttribute(name = "photo") CreatePhotoDto photoDto,
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
            return "add_ph";
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
            photoDto.setUserId(userService.get(username).getId());
            photoDto.setUrlPhoto(cloudinary.url().transformation(new Transformation().width(1000)
                .height(500)).imageTag(publicId));
            photoService.save(photoDto);
            return "photo_added";
        }
    }

    @GetMapping("/photos")
    public String getPhotos(Model model) {
        List<PhotoDto> photosDTO = photoService.getAll();
        Collections.reverse(photosDTO);
        LinkedHashMap<UserDto, PhotoDto> linkedHashMap = new LinkedHashMap<>();
        for (PhotoDto photoDTO : photosDTO) {
            linkedHashMap.put(userService.get(photoDTO.getUserId()), photoDTO);
        }
        model.addAttribute("photos", linkedHashMap);
        return "photos";
    }

    @GetMapping("/myPhotos")
    public String getMyPhotos(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserDto user = userService.get(username);
        int id = user.getId();
        List<PhotoDto> photosDTO = photoService.getAllByUserId(id);
        Collections.reverse(photosDTO);
        model.addAttribute("photos", photosDTO);
        return "my_photos";
    }
}
