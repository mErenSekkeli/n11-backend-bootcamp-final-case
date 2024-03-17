package com.merensekkeli.customerreviewservice.service.entityservice;

import com.merensekkeli.customerreviewservice.entity.Review;
import com.merensekkeli.customerreviewservice.general.BaseEntityService;
import com.merensekkeli.customerreviewservice.repository.ReviewRepository;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Map<String, Double> getCompanyRatings(List<String> companyIds) {
        List<Review> reviews = getRepository().findByCompanyIdIn(companyIds);

        return reviews.stream()
                .collect(Collectors.groupingBy(Review::getCompanyId,
                        Collectors.averagingInt(review -> review.getRate().getValue())));
    }

    public boolean existsByCustomerIdAndProductId(Long customerId, String companyId) {
        return getRepository().existsByCustomerIdAndCompanyId(customerId, companyId);
    }

    public List<Review> findByCustomerId(Long userId) {
        return getRepository().findByCustomerId(userId);
    }
}
