package com.merensekkeli.customerreviewservice.service;

import com.merensekkeli.customerreviewservice.entity.Review;
import com.merensekkeli.customerreviewservice.enums.EnumRate;
import com.merensekkeli.customerreviewservice.repository.ReviewRepository;
import com.merensekkeli.customerreviewservice.service.entityservice.ReviewEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewEntityServiceTest {

    @InjectMocks
    private ReviewEntityService reviewEntityService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateAverageRatingsForCompanies() {
        List<String> companyIds = Arrays.asList("1", "2");
        List<Review> reviews = Arrays.asList(
                new Review( 1L, 1L,  "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.FIVE, "Excellent service!"),
                new Review( 2L, 2L,  "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.FOUR, "Excellent service!"),
                new Review( 3L, 3L,  "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.THREE, "Excellent service!")
        );

        when(reviewRepository.findByCompanyIdIn(companyIds)).thenReturn(reviews);

        Map<String, Double> companyRatings = reviewEntityService.getCompanyRatings(companyIds);

        assertEquals(1, companyRatings.size());
        assertEquals(4, companyRatings.get("8511b204-06b2-4710-9d40-f8e2b61c701d"));
    }

    @Test
    void shouldReturnEmptyMapWhenNoReviewsExist() {
        List<String> companyIds = Arrays.asList("1", "2");

        when(reviewRepository.findByCompanyIdIn(companyIds)).thenReturn(List.of());

        Map<String, Double> companyRatings = reviewEntityService.getCompanyRatings(companyIds);

        assertEquals(0, companyRatings.size());
    }
}
