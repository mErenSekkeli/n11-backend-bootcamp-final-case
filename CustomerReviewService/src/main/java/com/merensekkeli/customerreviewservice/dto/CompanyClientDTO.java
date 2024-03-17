package com.merensekkeli.customerreviewservice.dto;

import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyClientDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private Double latitude;
    private Double longitude;
    private EnumStatus status;
    private Double averageRate;
}
