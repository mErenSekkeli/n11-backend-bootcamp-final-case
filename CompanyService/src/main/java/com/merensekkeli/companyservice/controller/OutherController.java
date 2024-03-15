package com.merensekkeli.companyservice.controller;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/outher")
@RequiredArgsConstructor
@Slf4j
public class OutherController {

    private final CompanyControllerContract companyControllerContract;

    @GetMapping("/companies")
    public List<CompanyDTO> getCompanies() {
        log.info("Outgoing request to company microservice for all companies");
        return companyControllerContract.getAllActiveCompanies();
    }

    @GetMapping("/company/{id}")
    public CompanyDTO getCompany(@PathVariable String id) {
        log.info("Outgoing request to company microservice for company with id: {}", id);
        return companyControllerContract.getCompanyById(id);
    }

}
