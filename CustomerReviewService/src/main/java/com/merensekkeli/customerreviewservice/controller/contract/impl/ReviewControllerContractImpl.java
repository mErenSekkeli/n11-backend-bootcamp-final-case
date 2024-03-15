package com.merensekkeli.customerreviewservice.controller.contract.impl;

import com.merensekkeli.customerreviewservice.client.CompanyClient;
import com.merensekkeli.customerreviewservice.controller.contract.ReviewControllerContract;
import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.dto.ReviewDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.entity.Review;
import com.merensekkeli.customerreviewservice.exception.ItemNotFoundException;
import com.merensekkeli.customerreviewservice.exception.ReviewAlreadyExistException;
import com.merensekkeli.customerreviewservice.mapper.ReviewMapper;
import com.merensekkeli.customerreviewservice.request.ReviewSaveRequest;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;
import com.merensekkeli.customerreviewservice.service.entityservice.CustomerEntityService;
import com.merensekkeli.customerreviewservice.service.entityservice.ReviewEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewControllerContractImpl implements ReviewControllerContract {

    private final ReviewEntityService reviewEntityService;
    private final CustomerEntityService customerEntityService;
    private final CompanyClient companyClient;

    @Override
    public ReviewDTO saveReview(ReviewSaveRequest request) {
        //check if customer already has a review for the product
        if(reviewEntityService.existsByCustomerIdAndProductId(request.getCustomerId(), request.getCompanyId())){
            throw new ReviewAlreadyExistException("Customer already has a review for the product with id: " +
                    request.getCompanyId() + " and customer id: " + request.getCustomerId());
        }
        //check if company exist
        CompanyClientDTO companyClientDTO = companyClient.getCompany(request.getCompanyId());
        if(companyClientDTO == null){
            throw new ItemNotFoundException("Company not found with id: " + request.getCompanyId());
        }

        Review review = ReviewMapper.INSTANCE.convertToReview(request);
        review = reviewEntityService.save(review);
        log.info("Review saved with id: {}", review.getId());
        Customer customer = customerEntityService.findByIdWithControl(request.getCustomerId());
        return ReviewMapper.INSTANCE.convertToReviewDTO(review, customer);
    }

    @Override
    public ReviewDTO getReview(Long id) {
        Review review = reviewEntityService.findByIdWithControl(id);
        log.info("Review found with id: {}", review.getId());
        Customer customer = customerEntityService.findByIdWithControl(review.getCustomerId());
        return ReviewMapper.INSTANCE.convertToReviewDTO(review, customer);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviewList = reviewEntityService.findAll();
        log.info("All reviews found");
        return reviewList.stream().map(userReview1 ->{
            Customer customer = customerEntityService.findByIdWithControl(userReview1.getCustomerId());
            return ReviewMapper.INSTANCE.convertToReviewDTO(userReview1, customer);
        }).toList();
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewUpdateRequest request) {
        Review review = reviewEntityService.findByIdWithControl(id);

        review = reviewEntityService.saveWithControl(review, request);
        log.info("Review updated with id: {}", review.getId());
        Customer customer = customerEntityService.findByIdWithControl(review.getCustomerId());
        return ReviewMapper.INSTANCE.convertToReviewDTO(review, customer);
    }

    @Override
    public void deleteReview(Long id) {
        reviewEntityService.delete(id);
        log.info("Review deleted with id: {}", id);
    }
}
