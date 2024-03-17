package com.merensekkeli.customerreviewservice.service;

import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.faker.CompanyFaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendationServiceTest {
    private RecommendationService recommendationService;

    @BeforeEach
    void setUp() {
        recommendationService = new RecommendationService();
    }

    @Test
    void shouldRecommendThreeClosestRestaurants() {
        Customer customer = new Customer();
        customer.setLatitude(40.712776);
        customer.setLongitude(-74.005974);

        CompanyClientDTO company1 = CompanyFaker.fakeCompany();
        company1.setId("1");
        company1.setLatitude(40.712776);
        company1.setLongitude(-74.005974);

        CompanyClientDTO company2 = CompanyFaker.fakeCompany();
        company2.setId("2");
        company2.setLatitude(40.712776);
        company2.setLongitude(-74.005974);

        CompanyClientDTO company3 = CompanyFaker.fakeCompany();
        company3.setId("3");
        company3.setLatitude(40.712776);
        company3.setLongitude(-74.005974);

        CompanyClientDTO company4 = CompanyFaker.fakeCompany();
        company4.setId("4");
        company4.setLatitude(40.712776);
        company4.setLongitude(-74.005974);

        List<CompanyClientDTO> allRestaurants = Arrays.asList(company1, company2, company3, company4);

        Map<String, Double> companyRatings = new HashMap<>();
        companyRatings.put("1", 5.0);
        companyRatings.put("2", 4.0);
        companyRatings.put("3", 3.0);
        companyRatings.put("4", 2.0);

        List<CompanyClientDTO> recommendedRestaurants = recommendationService.recommendRestaurants(allRestaurants, customer, companyRatings);

        assertEquals(3, recommendedRestaurants.size());
    }

    @Test
    void shouldRecommendNoRestaurantsWhenNoneAreClose() {
        Customer customer = new Customer();
        customer.setLatitude(40.712776);
        customer.setLongitude(-74.005974);

        CompanyClientDTO company1 = CompanyFaker.fakeCompany();
        company1.setId("1");
        company1.setLatitude(50.850346);
        company1.setLongitude(4.351721);

        List<CompanyClientDTO> allRestaurants = List.of(company1);

        Map<String, Double> companyRatings = new HashMap<>();
        companyRatings.put("1", 5.0);

        List<CompanyClientDTO> recommendedRestaurants = recommendationService.recommendRestaurants(allRestaurants, customer, companyRatings);

        assertEquals(0, recommendedRestaurants.size());
    }

}
