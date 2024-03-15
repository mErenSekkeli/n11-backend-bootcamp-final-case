package com.merensekkeli.customerreviewservice.service.entityservice;

import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.general.BaseEntityService;
import com.merensekkeli.customerreviewservice.repository.CustomerRepository;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerRepository> {

    protected CustomerEntityService(CustomerRepository repository) {
        super(repository);
    }
}
