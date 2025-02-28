package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

    public static final String NUMBER_OF_ELIGIBLE_MEMBERS = "Number of eligible members";
    public static final String NUMBER_OF_LAPSED_MEMBERS = "Number of lapsed members";
    public static final String NUMBER_OF_CANCELLED_MEMBERS = "Number of cancelled members";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REPORT_DATE")
    private Date reportDate;

    @Column(name = "INT_VALUE")
    private int intValue;

}
