package ru.kpfu.stud.rizrgaripov.controller;

import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.stud.rizrgaripov.dto.*;
import ru.kpfu.stud.rizrgaripov.model.Color;
import ru.kpfu.stud.rizrgaripov.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
public class MainController {
    private static final String BASE_URL = "https://x-colors.herokuapp.com/api/random/";
    private static final Logger logger
            = LoggerFactory.getLogger(MainController.class);

    Gson gson = new Gson();

    private final ReviewService reviewService;

    @Autowired
    public MainController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "main_page";
    }

    @GetMapping("/registration")
    public String getSignUp(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "registration";
    }

    @GetMapping("/signIn")
    public String getLogin(Model model) {
        model.addAttribute("user", new UserDto());
        return "signIn";
    }

    @GetMapping("/addArticle")
    public String getAddArticle(Model model) {
        model.addAttribute("article", new CreateArticleDto());
        return "add_article";
    }

    @GetMapping("/addPhoto")
    public String getAddPhoto(Model model) {
        model.addAttribute("photo", new CreatePhotoDto());
        return "add_ph";
    }

    @GetMapping("/addSaleAd")
    public String getAddSaleAd(Model model) {
        model.addAttribute("saleAd", new CreateSaleAdDto());
        return "add_sale_ad";
    }

    @GetMapping("/settings")
    public String getSettings(Model model) {
        model.addAttribute("user", new ChangeUserDto());
        return "settings";
    }

    @GetMapping("/uploadPhotoProfile")
    public String getUploadPhotoProfile(Model model) {
        model.addAttribute("user", new ChangePhotoProfileUrlDto());
        return "upload_photo_profile";
    }

    @GetMapping("/choosingColors")
    public String getChoosingColors() {
        return "choosing_colors";
    }

    @GetMapping("/getShade")
    public String getShade(Model model, HttpServletRequest httpServletRequest) {
        String colorParam = httpServletRequest.getParameter("color");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + colorParam)
                .build();

        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            Color color = gson.fromJson(Objects.requireNonNull(response.body()).string(), Color.class);
            color.setBackgroundColor("background-color:" + color.getHex() + ";");
            model.addAttribute("color", color);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "random_shade";
    }

    @GetMapping("/submitReview")
    public String getReviewForm(Model model) {
        model.addAttribute("review", new CreateReviewDto());
        return "review_form";
    }

    @PostMapping("/submitReview")
    public String postReview(@ModelAttribute(name = "review") CreateReviewDto reviewDto) {
        reviewService.save(reviewDto);
        return "review_added";
    }
}
