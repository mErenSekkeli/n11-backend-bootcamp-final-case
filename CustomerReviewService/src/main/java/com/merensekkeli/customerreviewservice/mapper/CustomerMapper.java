package com.merensekkeli.customerreviewservice.mapper;

import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    Customer convertToCustomer(CustomerSaveRequest request);

    CustomerDTO convertToCustomerDTO(Customer customer);


    @Mapping(target = "id", ignore = true)
    void updateCustomerFields(@MappingTarget Customer customer, CustomerUpdateRequest request);
}
