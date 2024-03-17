package com.merensekkeli.customerreviewservice.controller.contract;


import com.merensekkeli.customerreviewservice.dto.ReviewDTO;
import com.merensekkeli.customerreviewservice.request.ReviewSaveRequest;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;

import java.util.List;

public interface ReviewControllerContract {
    ReviewDTO saveReview(ReviewSaveRequest request);

    ReviewDTO getReview(Long id);

    List<ReviewDTO> getAllReviews();

    ReviewDTO updateReview(Long id, ReviewUpdateRequest request);

    void deleteReview(Long id);

    List<ReviewDTO> getReviewsByUserId(Long userId);

}
