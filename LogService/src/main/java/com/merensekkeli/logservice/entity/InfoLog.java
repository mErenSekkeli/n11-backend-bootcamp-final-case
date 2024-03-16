package com.merensekkeli.logservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "info_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoLog {

    @Id
    @GeneratedValue(generator = "InfoLog", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "InfoLog", sequenceName = "INFO_LOG_ID_SEQ")
    private Long id;

    private LocalDateTime date;
    private String message;
    private String description;
    private String serviceName;
}
