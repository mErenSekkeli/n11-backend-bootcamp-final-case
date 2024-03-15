package com.merensekkeli.customerreviewservice.controller.contract.impl;

import com.merensekkeli.customerreviewservice.client.CompanyClient;
import com.merensekkeli.customerreviewservice.controller.contract.RecommendationControllerContract;
import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.exception.ItemNotFoundException;
import com.merensekkeli.customerreviewservice.service.RecommendationService;
import com.merensekkeli.customerreviewservice.service.entityservice.CustomerEntityService;
import com.merensekkeli.customerreviewservice.service.entityservice.ReviewEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationControllerContractImpl implements RecommendationControllerContract {

    private final CompanyClient companyClient;
    private final CustomerEntityService customerEntityService;
    private final RecommendationService recommendationService;
    private final ReviewEntityService reviewEntityService;

    @Override
    public List<CompanyClientDTO> getRecommendedCompanies(Long id) {
        Optional<Customer> customer = customerEntityService.findById(id);
        if(customer.isEmpty()) {
            throw new ItemNotFoundException("Customer not found with id: " + id);
        }
        List<CompanyClientDTO> activeCompanies = companyClient.getCompanies();
        Map<String, Double> companyRatings = reviewEntityService.getCompanyRatings(activeCompanies.stream().map(CompanyClientDTO::id).toList());
        return recommendationService.recommendRestaurants(activeCompanies, customer.get(), companyRatings);
    }
}
