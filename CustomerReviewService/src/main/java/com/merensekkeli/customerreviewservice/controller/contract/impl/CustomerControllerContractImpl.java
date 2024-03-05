package com.merensekkeli.customerreviewservice.controller.contract.impl;

import com.merensekkeli.customerreviewservice.controller.contract.CustomerControllerContract;
import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerControllerContractImpl implements CustomerControllerContract {

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequest request) {
        return null;
    }
}
