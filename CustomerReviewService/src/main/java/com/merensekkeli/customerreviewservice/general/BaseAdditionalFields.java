package com.merensekkeli.customerreviewservice.general;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class BaseAdditionalFields {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
