package com.merensekkeli.customerreviewservice.controller;

import com.merensekkeli.customerreviewservice.controller.contract.CustomerControllerContract;
import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.general.RestResponse;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerControllerContract customerControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<CustomerDTO>> saveCustomer(@RequestBody CustomerSaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.saveCustomer(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.getCustomerById(id)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerDTO>>> getAllCustomers(){
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.getAllCustomers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerDTO>> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request){
        return ResponseEntity.ok(RestResponse.of(customerControllerContract.updateCustomer(id, request)));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerControllerContract.deleteCustomer(id);
    }
}
