package com.merensekkeli.companyservice.controller;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.general.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final KafkaProducerService kafkaProducerService;
    @Value("${application.name}")
    private String appName;

    @GetMapping("/companies")
    public List<CompanyDTO> getCompanies() {
        log.info("Outgoing request to company microservice for all companies");
        kafkaProducerService.sendMessage("infoLog", appName, "Outgoing request to company microservice for all companies");
        return companyControllerContract.getAllActiveCompanies();
    }

    @GetMapping("/company/{id}")
    public CompanyDTO getCompany(@PathVariable String id) {
        log.info("Outgoing request to company microservice for company with id: {}", id);
        kafkaProducerService.sendMessage("infoLog", appName, "Outgoing request to company microservice for company with id: " + id);
        return companyControllerContract.getCompanyByIdOuther(id);
    }

}
