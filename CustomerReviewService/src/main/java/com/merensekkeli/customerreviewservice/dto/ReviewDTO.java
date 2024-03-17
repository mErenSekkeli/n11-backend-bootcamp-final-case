package com.merensekkeli.customerreviewservice.dto;

import com.merensekkeli.customerreviewservice.enums.EnumRate;

import java.time.LocalDateTime;

public record ReviewDTO(
    String customerUsername,
    String companyId,
    EnumRate rate,
    String comment
) {
}
