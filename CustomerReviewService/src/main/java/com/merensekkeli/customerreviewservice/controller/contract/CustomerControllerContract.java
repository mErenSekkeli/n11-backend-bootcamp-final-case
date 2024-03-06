package com.merensekkeli.customerreviewservice.controller.contract;

import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;

import java.util.List;

public interface CustomerControllerContract {

    CustomerDTO saveCustomer(CustomerSaveRequest request);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerUpdateRequest request);

    void deleteCustomer(Long id);
}
