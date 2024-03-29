package com.merensekkeli.companyservice.controller.contract.impl;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.entity.Company;
import com.merensekkeli.companyservice.exception.ItemNotFoundException;
import com.merensekkeli.companyservice.general.KafkaProducerService;
import com.merensekkeli.companyservice.mapper.CompanyMapper;
import com.merensekkeli.companyservice.request.CompanySaveRequest;
import com.merensekkeli.companyservice.service.CompanyEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyControllerContractImpl implements CompanyControllerContract {

    private final CompanyEntityService companyEntityService;
    private final KafkaProducerService kafkaProducerService;
    @Value("${application.name}")
    private String appName;

    @Override
    public CompanyDTO saveCompany(CompanySaveRequest request) {
        Company company = CompanyMapper.INSTANCE.convertToCompany(request);
        company = companyEntityService.save(company);
        log.info("Company saved with id: {}", company.getId());
        kafkaProducerService.sendMessage("infoLog", appName, "Company saved with id: " + company.getId());
        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        Iterable<Company> companyIterable = companyEntityService.findAll();
        List<CompanyDTO> companyList = new ArrayList<>();
        companyIterable.forEach(company -> companyList.add(CompanyMapper.INSTANCE.convertToCompanyDTO(company)));
        log.info("All companies found");
        return companyList;
    }

    @Override
    public List<CompanyDTO> getAllActiveCompanies() {
        List<Company> companyList = companyEntityService.findAllActive();
        log.info("All active companies found");
        return companyList.stream().map(CompanyMapper.INSTANCE::convertToCompanyDTO).toList();
    }

    @Override
    public CompanyDTO updateCompany(String id, CompanySaveRequest request) {
        Company company = companyEntityService.findByIdWithControl(id);
        CompanyMapper.INSTANCE.updateCompanyFields(company, request);

        company = companyEntityService.save(company);
        log.info("Company updated with id: {}", company.getId());
        kafkaProducerService.sendMessage("infoLog", appName, "Company updated with id: " + company.getId());
        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

    @Override
    public void deleteCompany(String id) {
        Optional<Company> company = companyEntityService.findById(id);

        if (company.isEmpty()) {
            log.error("Company not found with id: {}", id);
            throw new ItemNotFoundException("Company not found with id: " + id);
        }
        companyEntityService.delete(id);
        log.info("Company deleted with id: {}", id);
        kafkaProducerService.sendMessage("infoLog", appName, "Company deleted with id: " + id);
    }

    @Override
    public List<CompanyDTO> findByNamed(String searchItem) {
        List<Company> companyList = companyEntityService.findByNamed(searchItem);
        log.info("Companies found with search item: {}", searchItem);
        kafkaProducerService.sendMessage("infoLog", appName, "Companies found with search item: " + searchItem);
        return companyList.stream().map(CompanyMapper.INSTANCE::convertToCompanyDTO).toList();
    }

    @Override
    public List<CompanyDTO> findByProperty(String searchItem) {
        List<Company> company = companyEntityService.findByProperties(searchItem);
        log.info("Company found with search item: {}", searchItem);
        kafkaProducerService.sendMessage("infoLog", appName, "Company found with search item: " + searchItem);
        return company.stream().map(CompanyMapper.INSTANCE::convertToCompanyDTO).toList();
    }

    @Override
    public CompanyDTO getCompanyById(String id) {
        Company company = companyEntityService.findByIdWithControl(id);
        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

    @Override
    public CompanyDTO getCompanyByIdOuther(String id) {
        Company company;
        try {
            company = companyEntityService.findByIdWithControl(id);
        } catch (ItemNotFoundException e) {
            log.error("Company not found with id: {}", id);
            return null;
        }

        return CompanyMapper.INSTANCE.convertToCompanyDTO(company);
    }

}
