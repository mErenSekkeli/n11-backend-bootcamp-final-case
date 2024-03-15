package com.merensekkeli.customerreviewservice.dto;

import com.merensekkeli.customerreviewservice.enums.EnumStatus;

public record CompanyClientDTO(
        String id,
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
