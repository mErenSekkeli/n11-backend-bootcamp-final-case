package com.merensekkeli.customerreviewservice.mapper;

import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    Customer convertToCustomer(CustomerSaveRequest request);

    CustomerDTO convertToCustomerDTO(Customer customer);

}
