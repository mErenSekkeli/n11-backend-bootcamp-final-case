package com.merensekkeli.customerreviewservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter

public class CustomerSaveRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^\\+(?:[0-9]●?){6,14}[0-9]$", message = "Invalid phone number format")
    private String phoneNumber;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    private EnumStatus status;
    @NotNull(message = "Latitude is mandatory")
    private Double latitude;
    @NotNull(message = "Longitude is mandatory")
    private Double longitude;
}
