package com.merensekkeli.customerreviewservice.controller;

import com.merensekkeli.customerreviewservice.controller.contract.ReviewControllerContract;
import com.merensekkeli.customerreviewservice.dto.ReviewDTO;
import com.merensekkeli.customerreviewservice.general.RestResponse;
import com.merensekkeli.customerreviewservice.request.ReviewSaveRequest;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewControllerContract reviewControllerContract;

    @PostMapping
    @Operation(summary = "Saves Review", description = "Saves review with given request")
    public ResponseEntity<RestResponse<ReviewDTO>> saveReview(@Valid @RequestBody ReviewSaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.saveReview(request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves Review", description = "Retrieves review by id")
    public ResponseEntity<RestResponse<ReviewDTO>> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.getReview(id)));
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "Retrieves Review List", description = "Retrieves all reviews by user id")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> getReviewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.getReviewsByUserId(userId)));
    }

    @GetMapping
    @Operation(summary = "Retrieves Review List", description = "Retrieves all reviews")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> getAllReviews() {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.getAllReviews()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates Review", description = "Updates review with given request")
    public ResponseEntity<RestResponse<ReviewDTO>> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) {
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.updateReview(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes Review", description = "Deletes review by id")
    public ResponseEntity<RestResponse<Void>> deleteReview(@PathVariable Long id) {
        reviewControllerContract.deleteReview(id);
        return ResponseEntity.ok(RestResponse.of(null));
    }
}
