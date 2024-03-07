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

    public Customer saveWithControl(Customer customer, CustomerUpdateRequest request) {
        if (request.getName() != null) {
            customer.setName(request.getName());
        }
        if (request.getSurname() != null) {
            customer.setSurname(request.getSurname());
        }
        if (request.getBirthDate() != null) {
            customer.setBirthDate(request.getBirthDate());
        }
        if (request.getLatitude() != null) {
            customer.setLatitude(request.getLatitude());
        }
        if (request.getLongitude() != null) {
            customer.setLongitude(request.getLongitude());
        }
        if (request.getStatus() != null) {
            customer.setStatus(request.getStatus());
        }
        return getRepository().save(customer);
    }
}
