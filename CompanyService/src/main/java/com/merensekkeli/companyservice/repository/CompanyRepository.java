package com.merensekkeli.companyservice.repository;

import com.merensekkeli.companyservice.entity.Company;
import com.merensekkeli.companyservice.enums.EnumStatus;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends SolrCrudRepository<Company, String> {

    Optional<Company> findById(String id);

    @Query(name = "Company.findByIdOrNameContains")
    List<Company> findyByNamed(String searchItem);

    @Query(name = "Company.findByProperties")
    List<Company> findByProperties(String searchItem);

    @Query(name = "Company.findByStatus")
    List<Company> findByStatus(EnumStatus status);
}
