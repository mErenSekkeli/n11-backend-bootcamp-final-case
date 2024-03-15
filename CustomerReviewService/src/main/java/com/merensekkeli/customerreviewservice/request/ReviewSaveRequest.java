package com.merensekkeli.customerreviewservice.request;

import com.merensekkeli.customerreviewservice.enums.EnumRate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReviewSaveRequest {
    @NotNull(message = "Customer id cannot be empty")
    private Long customerId;
    @NotBlank(message = "Company id cannot be empty")
    private String companyId;
    @NotNull(message = "Rate cannot be empty")
    private EnumRate rate;
    @Length(max = 500)
    private String comment;
}
