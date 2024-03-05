package com.merensekkeli.customerreviewservice.controller.contract;

import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;

public interface CustomerControllerContract {

    CustomerDTO saveCustomer(CustomerSaveRequest request);
}
