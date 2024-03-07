package com.merensekkeli.customerreviewservice.request;

import com.merensekkeli.customerreviewservice.enums.EnumRate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSaveRequest {

    private Long customerId;
    private Long companyId;
    private EnumRate rate;
    private String comment;
}
