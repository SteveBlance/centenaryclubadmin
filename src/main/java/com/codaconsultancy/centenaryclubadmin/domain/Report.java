package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reports")
public class Report {

    public static final String NUMBER_OF_ELIGIBLE_MEMBERS = "Number of eligible members";
    public static final String NUMBER_OF_LAPSED_MEMBERS = "Number of lapsed members";
    public static final String NUMBER_OF_CANCELLED_MEMBERS = "Number of cancelled members";

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "NAME")
    private String name;

    @Getter
    @Setter
    @Column(name = "REPORT_DATE")
    private Date reportDate;

    @Getter
    @Setter
    @Column(name = "INT_VALUE")
    private int intValue;

}
