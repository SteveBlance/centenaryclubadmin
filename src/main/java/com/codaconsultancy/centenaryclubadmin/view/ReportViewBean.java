package com.codaconsultancy.centenaryclubadmin.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportViewBean {

    private Long id;

    String reportType;

    public ReportViewBean() {
    }

    public ReportViewBean(String reportType) {
        this.reportType = reportType;
    }

    public ReportViewBean(Long id, String reportType) {
        this.id = id;
        this.reportType = reportType;
    }
}
