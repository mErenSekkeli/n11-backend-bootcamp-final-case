package com.merensekkeli.companyservice.controller;

import com.merensekkeli.companyservice.controller.contract.CompanyControllerContract;
import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.general.RestResponse;
import com.merensekkeli.companyservice.request.CompanySaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyControllerContract companyControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<CompanyDTO>> saveCompany(@RequestBody CompanySaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.saveCompany(request)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CompanyDTO>>> getAllCompanies() {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.getAllCompanies()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CompanyDTO>> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.getCompanyById(id)));
    }

    @GetMapping("/named")
    public ResponseEntity<RestResponse<List<CompanyDTO>>> getCompanyByName(@RequestParam String searchItem) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.findByNamed(searchItem)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<CompanyDTO>> updateCompany(@PathVariable Long id, @RequestBody CompanySaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(companyControllerContract.updateCompany(id, request)));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyControllerContract.deleteCompany(id);
    }
}
