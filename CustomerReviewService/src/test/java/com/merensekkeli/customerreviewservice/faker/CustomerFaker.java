package com.merensekkeli.customerreviewservice.faker;

import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;

import java.time.LocalDate;

public class CustomerFaker {

    public static Customer customer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setSurname("Doe");
        customer.setBirthDate(LocalDate.of(1980, 1, 1));
        customer.setUsername("johndoe");
        customer.setPhoneNumber("1234567890");
        customer.setEmail("johndoe@example.com");
        customer.setStatus(EnumStatus.ACTIVE);
        customer.setLatitude(40.712776);
        customer.setLongitude(-74.005974);
        return customer;
    }
}
