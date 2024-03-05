package com.merensekkeli.customerreviewservice.dto;


import com.merensekkeli.customerreviewservice.enums.EnumStatus;

import java.time.LocalDate;

public record CustomerDTO(
        String name,
        String surname,
        LocalDate birthDate,
        String username,
        String phoneNumber,
        String email,
        EnumStatus status,
        Double latitude,
        Double longitude
) {
}
