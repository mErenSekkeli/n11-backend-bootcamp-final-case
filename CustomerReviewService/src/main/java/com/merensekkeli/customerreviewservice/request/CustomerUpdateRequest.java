package com.merensekkeli.customerreviewservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerUpdateRequest {

        private String name;
        private String surname;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDate;
        private Double latitude;
        private Double longitude;
        private EnumStatus status;
}
