package com.merensekkeli.companyservice.request;

import com.merensekkeli.companyservice.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanySaveRequest {

    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private Double latitude;
    private Double longitude;
    private EnumStatus status;
}
