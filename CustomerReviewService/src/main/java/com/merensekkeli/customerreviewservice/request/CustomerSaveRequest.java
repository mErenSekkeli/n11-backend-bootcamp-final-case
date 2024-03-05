package com.merensekkeli.customerreviewservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerSaveRequest {

    private String name;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String username;
    private String phoneNumber;
    private String email;
    private EnumStatus status;
    private Double latitude;
    private Double longitude;
}
