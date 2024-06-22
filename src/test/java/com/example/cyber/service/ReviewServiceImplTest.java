package com.example.cyber.service;

import com.example.cyber.model.Review;
import com.example.cyber.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReviewServiceImplTest {

    private ReviewRepository reviewRepository;
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        reviewRepository = Mockito.mock(ReviewRepository.class);
        reviewService = new ReviewServiceImpl(reviewRepository);
    }

    @DisplayName("Test createReview method")
    @Test
    void createReview() {
        Review review = new Review();
        when(reviewRepository.save(review)).thenReturn(review);

        Review createdReview = reviewService.createReview(review);
        assertEquals(review, createdReview);
    }

    @DisplayName("Test getReviewById method")
    @Test
    void getReviewById() {
        Review review = new Review();
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Review foundReview = reviewService.getReviewById(1L);
        assertEquals(review, foundReview);
    }

    @DisplayName("Test getAllReviews method")
    @Test
    void getAllReviews() {
        Review review = new Review();
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        List<Review> reviews = reviewService.getAllReviews();
        assertEquals(1, reviews.size());
        assertEquals(review, reviews.get(0));
    }

    @DisplayName("Test deleteReview method")
    @Test
    void deleteReview() {
        reviewService.deleteReview(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }
}
