package com.codaconsultancy.centenaryclubadmin.view;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
