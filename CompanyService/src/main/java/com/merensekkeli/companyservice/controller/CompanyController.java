package com.merensekkeli.companyservice.controller;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.general.RestResponse;
import com.merensekkeli.companyservice.request.CompanySaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyControllerContract companyControllerContract;

    @PostMapping
    @Operation(summary = "Saves Company", description = "Saves company with given request")
    public ResponseEntity<RestResponse<CompanyDTO>> saveCompany(@Valid @RequestBody CompanySaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.saveCompany(request)));
    }

    @GetMapping
    @Operation(summary = "Retrieves Company List", description = "Retrieves all companies")
    public ResponseEntity<RestResponse<List<CompanyDTO>>> getAllCompanies() {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.getAllCompanies()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves Company", description = "Retrieves company by id")
    public ResponseEntity<RestResponse<CompanyDTO>> getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.getCompanyById(id)));
    }

    @GetMapping("/property")
    @Operation(summary = "Retrieves Company List", description = "Retrieves company by address, phone, email or status")
    public ResponseEntity<RestResponse<List<CompanyDTO>>> getCompanyByProperties(@RequestParam String searchItem) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.findByProperty(searchItem)));
    }

    @GetMapping("/named")
    @Operation(summary = "Retrieves Company List", description = "Retrieves company by name or id")
    public ResponseEntity<RestResponse<List<CompanyDTO>>> getCompanyByName(@RequestParam String searchItem) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.findByNamed(searchItem)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates Company", description = "Updates company with given request")
    public ResponseEntity<RestResponse<CompanyDTO>> updateCompany(@PathVariable String id, @RequestBody CompanySaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.updateCompany(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes Company", description = "Deletes company by id")
    public void deleteCompany(@PathVariable String id) {
        companyControllerContract.deleteCompany(id);
    }
}
