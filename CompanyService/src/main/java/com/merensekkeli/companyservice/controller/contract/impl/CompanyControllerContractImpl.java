package com.merensekkeli.companyservice.controller.contract.impl;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.entity.Company;
import com.merensekkeli.companyservice.mapper.CompanyMapper;
import com.merensekkeli.companyservice.request.CompanySaveRequest;
import com.merensekkeli.companyservice.service.CompanyEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyControllerContractImpl implements CompanyControllerContract {

    private final CompanyEntityService companyEntityService;

    @Override
    public CompanyDTO saveCompany(CompanySaveRequest request) {
        Company company = CompanyMapper.INSTANCE.convertToCompany(request);
        company = companyEntityService.save(company);
        log.info("Company saved with id: {}", company.getId());
        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companyList = companyEntityService.findAll();
        log.info("All companies found");
        return companyList.stream().map(CompanyMapper.INSTANCE::convertToCompanyDTO).toList();
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyEntityService.findByIdWithControl(id);
        log.info("Company found with id: {}", company.getId());
        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanySaveRequest request) {
        Company company = companyEntityService.findByIdWithControl(id);
        CompanyMapper.INSTANCE.updateCompanyFields(company, request);

        company = companyEntityService.save(company);
        log.info("Company updated with id: {}", company.getId());
        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyEntityService.delete(id);
        log.info("Company deleted with id: {}", id);
    }

}
