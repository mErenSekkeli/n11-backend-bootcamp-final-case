package com.merensekkeli.companyservice.mapper;

import com.merensekkeli.companyservice.dto.CompanyDTO;
import com.merensekkeli.companyservice.entity.Company;
import com.merensekkeli.companyservice.request.CompanySaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company convertToCompany(CompanySaveRequest request);

    CompanyDTO convertToCompanyDTO(Company company);

    @Mapping(target = "id", ignore = true)
    void updateCompanyFields(@MappingTarget Company company, CompanySaveRequest request);
}
