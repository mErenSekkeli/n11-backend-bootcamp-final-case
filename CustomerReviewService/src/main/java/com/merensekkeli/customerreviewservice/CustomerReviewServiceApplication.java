package com.merensekkeli.customerreviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerReviewServiceApplication.class, args);
	}

}
