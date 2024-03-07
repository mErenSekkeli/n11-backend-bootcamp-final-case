package com.merensekkeli.companyservice.controller.contract;


import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.request.CompanySaveRequest;

import java.util.List;

public interface CompanyControllerContract {
    CompanyDTO saveCompany(CompanySaveRequest request);

    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(Long id);

    CompanyDTO updateCompany(Long id, CompanySaveRequest request);

    void deleteCompany(Long id);
}
