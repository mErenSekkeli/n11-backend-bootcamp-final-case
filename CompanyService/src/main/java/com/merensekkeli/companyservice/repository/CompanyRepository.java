package com.merensekkeli.companyservice.repository;

import com.merensekkeli.companyservice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
