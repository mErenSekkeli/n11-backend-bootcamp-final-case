package com.merensekkeli.customerreviewservice.dto;

import com.merensekkeli.customerreviewservice.enums.EnumRate;

import java.time.LocalDateTime;

public record ReviewDTO(
    String customerUsername,
    String companyId, //TODO: change to company name, for now it's just a company id
    EnumRate rate,
    String comment
) {
}
