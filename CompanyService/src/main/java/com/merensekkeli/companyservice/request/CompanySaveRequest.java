package com.merensekkeli.companyservice.request;

import com.merensekkeli.companyservice.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CompanySaveRequest {

    @NotBlank
    @Length(min = 1, max = 255)
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp="^\\+(?:[0-9]●?){6,14}[0-9]$", message="Invalid phone number format")
    private String phone;
    private String email;
    private String website;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private EnumStatus status;
}
