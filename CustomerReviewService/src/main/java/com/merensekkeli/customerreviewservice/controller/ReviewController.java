package com.merensekkeli.customerreviewservice.controller;

import com.merensekkeli.customerreviewservice.controller.contract.ReviewControllerContract;
import com.merensekkeli.customerreviewservice.dto.ReviewDTO;
import com.merensekkeli.customerreviewservice.general.RestResponse;
import com.merensekkeli.customerreviewservice.request.ReviewSaveRequest;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewControllerContract reviewControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<ReviewDTO>> saveReview(@RequestBody ReviewSaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.saveReview(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewDTO>> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.getReview(id)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<ReviewDTO>>> getAllReviews() {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.getAllReviews()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewDTO>> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.updateReview(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteReview(@PathVariable Long id) {
        reviewControllerContract.deleteReview(id);
        return ResponseEntity.ok(RestResponse.of(null));
    }
}
