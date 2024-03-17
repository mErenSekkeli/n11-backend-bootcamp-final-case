package com.merensekkeli.companyservice.controller;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.general.KafkaProducerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/outher")
@RequiredArgsConstructor
@Slf4j
public class OutherController {

    private final CompanyControllerContract companyControllerContract;
    private final KafkaProducerService kafkaProducerService;
    @Value("${application.name}")
    private String appName;

    @GetMapping("/companies")
    @Operation(summary = "Returns Company List", description = "Returns the data of all active companies to the outher service.")
    public List<CompanyDTO> getCompanies() {
        log.info("Outgoing request to company microservice for all companies");
        kafkaProducerService.sendMessage("infoLog", appName, "Outgoing request to company microservice for all companies");
        return companyControllerContract.getAllActiveCompanies();
    }

    @GetMapping("/company/{id}")
    @Operation(summary = "Returns Company", description = "Returns the data of the company with the given id to the outher service.")
    public CompanyDTO getCompany(@PathVariable String id) {
        log.info("Outgoing request to company microservice for company with id: {}", id);
        kafkaProducerService.sendMessage("infoLog", appName, "Outgoing request to company microservice for company with id: " + id);
        return companyControllerContract.getCompanyByIdOuther(id);
    }

}
