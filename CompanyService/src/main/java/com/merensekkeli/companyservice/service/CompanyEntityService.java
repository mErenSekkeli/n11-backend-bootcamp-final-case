package com.merensekkeli.companyservice.service;

import com.merensekkeli.companyservice.entity.Company;
import com.merensekkeli.companyservice.general.BaseEntityService;
import com.merensekkeli.companyservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyEntityService extends BaseEntityService<Company, CompanyRepository> {

    protected CompanyEntityService(CompanyRepository repository) {
        super(repository);
    }

    public List<Company> findByNamed(String searchItem) {
        return getRepository().findyByNamed(searchItem);
    }
}
