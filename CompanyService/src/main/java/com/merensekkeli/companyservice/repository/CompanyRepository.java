package com.merensekkeli.companyservice.repository;

import com.merensekkeli.companyservice.entity.Company;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface CompanyRepository extends SolrCrudRepository<Company, Long> {

    @Query(name = "Company.findByIdOrNameContains")
    List<Company> findyByNamed(String searchItem);
}
