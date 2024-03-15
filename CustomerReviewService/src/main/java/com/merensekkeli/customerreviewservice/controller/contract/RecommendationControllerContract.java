package com.merensekkeli.customerreviewservice.controller.contract;

import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;

import java.util.List;

public interface RecommendationControllerContract {

    List<CompanyClientDTO> getRecommendedCompanies(Long id);
}
