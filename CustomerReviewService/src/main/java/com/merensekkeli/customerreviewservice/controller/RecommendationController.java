package com.merensekkeli.customerreviewservice.controller;

import com.merensekkeli.customerreviewservice.client.CompanyClient;
import com.merensekkeli.customerreviewservice.controller.contract.RecommendationControllerContract;
import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.general.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final CompanyClient companyClient;
    private final RecommendationControllerContract recommendationControllerContract;

    @GetMapping("/companies")
    @Operation(summary = "Retrieves Company List", description = "Retrieves all companies from CompanyService")
    public ResponseEntity<RestResponse<List<CompanyClientDTO>>> getCompanies() {
        return ResponseEntity.ok(RestResponse.of(companyClient.getCompanies()));
    }

    @GetMapping("/company/{id}")
    @Operation(summary = "Retrieves Company", description = "Retrieves company from CompanyService by id")
    public ResponseEntity<RestResponse<CompanyClientDTO>> getCompany(@PathVariable String id) {
        return ResponseEntity.ok(RestResponse.of(companyClient.getCompany(id)));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Retrieves Recommended Companies", description = "Retrieves maximum 3 recommended companies from CompanyService by user id")
    public ResponseEntity<RestResponse<List<CompanyClientDTO>>> getRecommendedCompanies(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(recommendationControllerContract.getRecommendedCompanies(id)));
    }

}
