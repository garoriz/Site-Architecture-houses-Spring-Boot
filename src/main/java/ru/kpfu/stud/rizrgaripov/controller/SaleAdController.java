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
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.stud.rizrgaripov.dto.*;
import ru.kpfu.stud.rizrgaripov.service.SaleAdService;
import ru.kpfu.stud.rizrgaripov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class SaleAdController {
    private static final int DIRECTORIES_COUNT = 100;
    private static final String FILE_PATH_PREFIX = "/tmp";
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dfn48aqa5",
            "api_key", "591695994741495",
            "api_secret", "fWcdsdRM6uR3o5NdZDGAzVli5bo"));
    private static final Logger logger
            = LoggerFactory.getLogger(SaleAdController.class);

    private final SaleAdService saleAdService;
    private final UserService userService;

    @Autowired
    public SaleAdController(SaleAdService saleAdService, UserService userService) {
        this.saleAdService = saleAdService;
        this.userService = userService;
    }

    @PostMapping("/addSaleAd")
    public String addSaleAd(@ModelAttribute(name = "saleAd") CreateSaleAdDto createSaleAdDto, HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        createSaleAdDto.setUserId(userService.get(username).getId());

        String urlPhoto = "";
        Part part = null;
        try {
            part = request.getPart("file");
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage());
        }
        if (part.getSize() != 0) {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            InputStream contentPhoto = null;
            try {
                contentPhoto = part.getInputStream();
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
                buffer = new byte[contentPhoto.available()];
                contentPhoto.read(buffer);
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
            urlPhoto = cloudinary.url().transformation(new Transformation().width(1000)
                    .height(500)).imageTag(publicId);
        }

        createSaleAdDto.setUrlPhoto(urlPhoto);
        saleAdService.save(createSaleAdDto);
        return "sale_ad_added";
    }

    @GetMapping("/saleAds")
    public String getSaleAds(Model model) {
        List<SaleAdDto> saleAdsDto = saleAdService.getAll();
        Collections.reverse(saleAdsDto);
        model.addAttribute("saleAds", saleAdsDto);
        return "sale_ads";
    }

    @PostMapping("/saleAds")
    public String postSaleAds(Model model, HttpServletRequest request) {
        String searchName = "";
        int maxPrice = Integer.MAX_VALUE;
        if (request.getParameter("searchName") != null) {
            searchName = request.getParameter("searchName");
        }
        if (request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").equals("")) {
            maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
        }
        List<SaleAdDto> saleAdsDto = saleAdService.getAllByHeadingAndPrice(searchName, maxPrice);
        Collections.reverse(saleAdsDto);
        model.addAttribute("saleAds", saleAdsDto);
        return "sale_ads";
    }

    @GetMapping("/saleAd")
    public String getSaleAd(Model modelSaleAd, Model modelUser, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        SaleAdDto saleAdDto = saleAdService.get(id);
        UserDto userDto = userService.get(saleAdDto.getUserId());
        modelSaleAd.addAttribute("saleAd", saleAdDto);
        modelUser.addAttribute("user", userDto);
        return "sale_ad";
    }

    @GetMapping("/mySaleAds")
    public String getMySaleAds(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserDto user = userService.get(username);
        int id = user.getId();
        List<SaleAdDto> saleAdsDto = saleAdService.getAllByUserId(id);
        Collections.reverse(saleAdsDto);
        model.addAttribute("saleAds", saleAdsDto);
        return "my_sale_ads";
    }

    @GetMapping("/deleteSaleAd")
    public String deleteSaleAd(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        saleAdService.deleteById(id);
        return "sale_ad_deleted";
    }

    @GetMapping("/updateSaleAd")
    public String getUpdateSaleAd(Model modelSaleAd, Model modelChangeSaleAd, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        SaleAdDto saleAdDto = saleAdService.get(id);
        modelSaleAd.addAttribute("saleAd", saleAdDto);

        modelChangeSaleAd.addAttribute("changeSaleAd", new ChangeSaleAdDto());
        return "update_sale_ad";
    }

    @PostMapping(value = "/updateSaleAd", params = "action=submit")
    public String updateSaleAd(
            HttpServletRequest request,
            @RequestParam("heading") String heading,
            @RequestParam("content") String content,
            @RequestParam("price") int price,
            @RequestParam("phoneNumber") String phoneNumber
            ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        String urlPhoto = "";
        Part part = null;
        try {
            part = request.getPart("file");
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage());
        }
        if (part.getSize() != 0) {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            InputStream contentPhoto = null;
            try {
                contentPhoto = part.getInputStream();
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
                buffer = new byte[contentPhoto.available()];
                contentPhoto.read(buffer);
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
            urlPhoto = cloudinary.url().transformation(new Transformation().width(1000)
                    .height(500)).imageTag(publicId);
        }
        int id = Integer.parseInt(request.getParameter("id"));
        saleAdService.update(
                id,
                heading,
                content,
                price,
                phoneNumber,
                urlPhoto
        );
        return "sale_ad_updated";
    }
}
