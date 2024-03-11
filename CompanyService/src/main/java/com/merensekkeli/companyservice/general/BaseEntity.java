package com.merensekkeli.companyservice.general;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel {


    @Embedded
    private BaseAdditionalFields baseAdditionalFields;

}
