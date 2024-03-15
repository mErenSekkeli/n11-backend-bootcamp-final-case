package com.merensekkeli.customerreviewservice.repository;

import com.merensekkeli.customerreviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyIdIn(List<String> companyIds);

    boolean existsByCustomerIdAndCompanyId(Long customerId, String companyId);
}
