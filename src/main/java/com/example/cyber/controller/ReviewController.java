package com.example.cyber.controller;

import com.example.cyber.model.Review;
import com.example.cyber.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(review));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/top")
    public ResponseEntity<List<Review>> getTopReviewsByRating() {
        return ResponseEntity.ok(reviewService.getTopReviewsByRating());
    }


    @GetMapping("/sorted-by-rating")
    public ResponseEntity<List<Review>> getReviewsSortedByRating(@RequestParam boolean ascending) {
        return ResponseEntity.ok(reviewService.getAllReviewsSortedByRating(ascending));
    }

    @GetMapping("/sorted-by-date")
    public ResponseEntity<List<Review>> getReviewsSortedByDate(@RequestParam boolean ascending) {
        return ResponseEntity.ok(reviewService.getAllReviewsSortedByDate(ascending));
    }

}
