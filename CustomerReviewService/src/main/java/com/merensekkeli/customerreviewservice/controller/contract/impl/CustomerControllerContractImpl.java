package com.merensekkeli.customerreviewservice.controller.contract.impl;

import com.merensekkeli.customerreviewservice.controller.contract.CustomerControllerContract;
import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.exception.ItemNotFoundException;
import com.merensekkeli.customerreviewservice.general.KafkaProducerService;
import com.merensekkeli.customerreviewservice.mapper.CustomerMapper;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import com.merensekkeli.customerreviewservice.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerControllerContractImpl implements CustomerControllerContract {

    private final CustomerEntityService customerEntityService;
    private final KafkaProducerService kafkaProducerService;
    @Value("${application.name}")
    private String appName;

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequest request) {
        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(request);
        customer = customerEntityService.save(customer);
        log.info("Customer saved with id: {}", customer.getId());
        kafkaProducerService.sendMessage("infoLog", appName, "Customer saved with id: " + customer.getId());
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
        kafkaProducerService.sendMessage("infoLog", appName, "Customer updated with id: " + customer.getId());
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerEntityService.findById(id);
        if (customer.isEmpty()) {
            log.error("Customer not found with id: {}", id);
            throw new ItemNotFoundException("Customer not found with id: " + id);
        }
        customerEntityService.delete(id);
        kafkaProducerService.sendMessage("infoLog", appName, "Customer deleted with id: " + id);
        log.info("Customer deleted with id: {}", id);
    }
}
