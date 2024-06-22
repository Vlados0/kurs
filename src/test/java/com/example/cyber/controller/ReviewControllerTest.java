package com.example.cyber.controller;

import com.example.cyber.model.Review;
import com.example.cyber.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    private final ReviewService reviewService = Mockito.mock(ReviewService.class);
    private final ReviewController reviewController = new ReviewController(reviewService);

    @DisplayName("Test createReview method")
    @Test
    void createReview() {
        Review review = new Review();
        when(reviewService.createReview(review)).thenReturn(review);

        ResponseEntity<Review> response = reviewController.addReview(review);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @DisplayName("Test getReviewsByProductId method")
    @Test
    void getReviewsByProductId() {
        Long productId = 1L;
        List<Review> reviews = Collections.singletonList(new Review());
        when(reviewService.getReviewsByProductId(productId)).thenReturn(reviews);

        ResponseEntity<List<Review>> response = reviewController.getReviewsByProductId(productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @DisplayName("Test getReviewById method")
    @Test
    void getReviewById() {
        Long reviewId = 1L;
        Review review = new Review();
        when(reviewService.getReviewById(reviewId)).thenReturn(review);

        ResponseEntity<Review> response = reviewController.getReviewById(reviewId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @DisplayName("Test getAllReviews method")
    @Test
    void getAllReviews() {
        List<Review> reviews = Collections.singletonList(new Review());
        when(reviewService.getAllReviews()).thenReturn(reviews);

        ResponseEntity<List<Review>> response = reviewController.getAllReviews();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @DisplayName("Test deleteReview method")
    @Test
    void deleteReview() {
        Long reviewId = 1L;
        ResponseEntity<Void> response = reviewController.deleteReview(reviewId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reviewService, times(1)).deleteReview(reviewId);
    }
}
