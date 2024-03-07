package com.merensekkeli.customerreviewservice.entity;

import com.merensekkeli.customerreviewservice.enums.EnumRate;
import com.merensekkeli.customerreviewservice.general.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Review")
    @SequenceGenerator(name = "Review", sequenceName = "REVIEW_ID_SEQ", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "rate", nullable = false)
    private EnumRate rate;

    @Column(name = "comment", length = 500)
    private String comment;
}
