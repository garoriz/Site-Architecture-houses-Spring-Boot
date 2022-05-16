package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.CreateReviewDto;
import ru.kpfu.stud.rizrgaripov.model.Review;
import ru.kpfu.stud.rizrgaripov.repository.ReviewRepository;
import ru.kpfu.stud.rizrgaripov.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void save(CreateReviewDto createReviewDto) {
        Review review = new Review(
                createReviewDto.getAuthorName(),
                createReviewDto.getRating(),
                createReviewDto.getContent()
        );
        reviewRepository.save(review);
    }
}
