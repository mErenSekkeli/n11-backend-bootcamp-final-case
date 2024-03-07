package com.merensekkeli.companyservice.dto;

import com.merensekkeli.companyservice.enums.EnumStatus;

public record CompanyDTO(
        String name,
        String address,
        String phone,
        String email,
        String website,
        Double latitude,
        Double longitude,
        EnumStatus status
) {
}
