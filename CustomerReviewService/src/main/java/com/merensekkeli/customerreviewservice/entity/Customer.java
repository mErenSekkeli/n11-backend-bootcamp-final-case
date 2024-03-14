package com.merensekkeli.customerreviewservice.entity;

import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import com.merensekkeli.customerreviewservice.general.BaseEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer")
    @SequenceGenerator(name = "Customer", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "surname", length = 100, nullable = false)
    private String surname;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private EnumStatus status;

    @Column(name = "latitude", length = 100, nullable = false)
    private Double latitude;

    @Column(name = "longitude", length = 100, nullable = false)
    private Double longitude;

}