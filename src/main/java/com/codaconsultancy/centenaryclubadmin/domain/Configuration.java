package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "configuration")
public class Configuration {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "NAME")
    private String name;

    @Getter
    @Setter
    @Column(name = "STRING_VALUE")
    private String stringValue;

    @Getter
    @Setter
    @Column(name = "BOOLEAN_VALUE")
    private Boolean booleanValue;

    @Getter
    @Setter
    @Column(name = "DATE_VALUE")
    private Date dateValue;
}
