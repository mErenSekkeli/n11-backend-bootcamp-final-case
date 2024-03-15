package com.merensekkeli.companyservice.controller.contract;


import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.request.CompanySaveRequest;

import java.util.List;

public interface CompanyControllerContract {
    CompanyDTO saveCompany(CompanySaveRequest request);

    List<CompanyDTO> getAllCompanies();

    List<CompanyDTO> getAllActiveCompanies();

    CompanyDTO updateCompany(String id, CompanySaveRequest request);

    void deleteCompany(String id);

    List<CompanyDTO> findByNamed(String searchItem);

    List<CompanyDTO> findByProperty(String searchItem);

    CompanyDTO getCompanyById(String id);
}
