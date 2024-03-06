package com.merensekkeli.customerreviewservice.repository;

import com.merensekkeli.customerreviewservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
