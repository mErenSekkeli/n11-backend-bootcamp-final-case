package com.merensekkeli.customerreviewservice.controller.contract.impl;

import com.merensekkeli.customerreviewservice.controller.contract.CustomerControllerContract;
import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.mapper.CustomerMapper;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import com.merensekkeli.customerreviewservice.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerControllerContractImpl implements CustomerControllerContract {

    private final CustomerEntityService customerEntityService;

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequest request) {
        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(request);
        customer = customerEntityService.save(customer);
        log.info("Customer saved with id: {}", customer.getId());
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerEntityService.findByIdWithControl(id);
        log.info("Customer found with id: {}", customer.getId());
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerEntityService.findAll();
        log.info("All customers found");
        return customerList.stream().map(CustomerMapper.INSTANCE::convertToCustomerDTO).toList();
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerUpdateRequest request) {
        Customer customer = customerEntityService.findByIdWithControl(id);
        CustomerMapper.INSTANCE.updateCustomerFields(customer, request);
        customer = customerEntityService.save(customer);
        log.info("Customer updated with id: {}", customer.getId());
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerEntityService.delete(id);
        log.info("Customer deleted with id: {}", id);
    }
}
