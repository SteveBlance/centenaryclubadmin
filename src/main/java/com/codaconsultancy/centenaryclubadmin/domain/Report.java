package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;

import java.util.Date;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
}
