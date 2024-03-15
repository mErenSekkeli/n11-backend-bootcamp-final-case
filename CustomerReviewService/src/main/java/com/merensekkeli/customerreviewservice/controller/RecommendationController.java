package com.merensekkeli.customerreviewservice.controller;

import com.merensekkeli.customerreviewservice.client.CompanyClient;
import com.merensekkeli.customerreviewservice.controller.contract.RecommendationControllerContract;
import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final CompanyClient companyClient;
    private final RecommendationControllerContract recommendationControllerContract;

    @GetMapping("/companies")
    public ResponseEntity<RestResponse<List<CompanyClientDTO>>> getCompanies() {
        return ResponseEntity.ok(RestResponse.of(companyClient.getCompanies()));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<RestResponse<CompanyClientDTO>> getCompany(@PathVariable String id) {
        return ResponseEntity.ok(RestResponse.of(companyClient.getCompany(id)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<RestResponse<List<CompanyClientDTO>>> getRecommendedCompanies(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(recommendationControllerContract.getRecommendedCompanies(id)));
    }

}
