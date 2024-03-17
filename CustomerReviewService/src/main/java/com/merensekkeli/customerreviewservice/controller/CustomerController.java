package com.merensekkeli.customerreviewservice.controller;

import com.merensekkeli.customerreviewservice.controller.contract.CustomerControllerContract;
import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.general.RestResponse;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerControllerContract customerControllerContract;

    @PostMapping
    @Operation(summary = "Saves Customer", description = "Saves customer with given request")
    public ResponseEntity<RestResponse<CustomerDTO>> saveCustomer(@Valid @RequestBody CustomerSaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.saveCustomer(request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves Customer", description = "Retrieves customer by id")
    public ResponseEntity<RestResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.getCustomerById(id)));
    }

    @GetMapping
    @Operation(summary = "Retrieves Customer List", description = "Retrieves all customers")
    public ResponseEntity<RestResponse<List<CustomerDTO>>> getAllCustomers(){
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.getAllCustomers()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates Customer", description = "Updates customer with given request")
    public ResponseEntity<RestResponse<CustomerDTO>> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request){
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.updateCustomer(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes Customer", description = "Deletes customer by id")
    public void deleteCustomer(@PathVariable Long id){
        customerControllerContract.deleteCustomer(id);
    }
}
