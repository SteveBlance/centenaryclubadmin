package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "configuration")
public class Configuration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "STRING_VALUE")
    private String stringValue;

    @Column(name = "BOOLEAN_VALUE")
    private Boolean booleanValue;

    @Column(name = "DATE_VALUE")
    private Date dateValue;
}
