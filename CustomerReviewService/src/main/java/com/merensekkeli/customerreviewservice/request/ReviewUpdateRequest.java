package com.merensekkeli.customerreviewservice.request;

import com.merensekkeli.customerreviewservice.enums.EnumRate;



public record ReviewUpdateRequest(
        String comment,
        EnumRate rate
) {
}
