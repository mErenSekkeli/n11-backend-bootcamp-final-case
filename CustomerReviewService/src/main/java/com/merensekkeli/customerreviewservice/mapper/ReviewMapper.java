package com.merensekkeli.customerreviewservice.mapper;

import com.merensekkeli.customerreviewservice.dto.ReviewDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.entity.Review;
import com.merensekkeli.customerreviewservice.request.ReviewSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review convertToReview(ReviewSaveRequest request);

    @Mapping(target = "customerUsername", source = "customer.username")
    ReviewDTO convertToReviewDTO(Review review, Customer customer);
}
