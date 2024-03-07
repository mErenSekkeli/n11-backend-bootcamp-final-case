package com.merensekkeli.customerreviewservice.repository;

import com.merensekkeli.customerreviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
