package com.merensekkeli.companyservice.entity;

import com.merensekkeli.companyservice.enums.EnumStatus;
import com.merensekkeli.companyservice.general.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@SolrDocument(collection = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "address", type = "string")
    private String address;

    @Indexed(name = "phone", type = "string")
    private String phone;

    @Indexed(name = "email", type = "string")
    private String email;

    @Indexed(name = "website", type = "string")
    private String website;

    @Indexed(name = "latitude", type = "pdouble")
    private Double latitude;

    @Indexed(name = "longitude", type = "pdouble")
    private Double longitude;

    @Indexed(name = "status", type = "string")
    private EnumStatus status;
}
