package com.merensekkeli.customerreviewservice.service.entityservice;

import com.merensekkeli.customerreviewservice.entity.Review;
import com.merensekkeli.customerreviewservice.general.BaseEntityService;
import com.merensekkeli.customerreviewservice.repository.ReviewRepository;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewEntityService extends BaseEntityService<Review, ReviewRepository> {

    protected ReviewEntityService(ReviewRepository repository) {
        super(repository);
    }

    public Review saveWithControl(Review review, ReviewUpdateRequest request) {
        if (request.comment() != null)
            review.setComment(request.comment());
        if (request.rate() != null)
            review.setRate(request.rate());
        return save(review);
    }

}
