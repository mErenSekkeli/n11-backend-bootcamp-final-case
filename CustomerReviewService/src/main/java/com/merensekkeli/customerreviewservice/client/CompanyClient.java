package com.merensekkeli.customerreviewservice.client;

import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "company", url = "http://localhost:8081/api/v1/outher")
public interface CompanyClient {

    @GetMapping("/companies")
    List<CompanyClientDTO> getCompanies();

    @GetMapping("/company/{id}")
    CompanyClientDTO getCompany(@PathVariable String id);

}
