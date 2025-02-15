package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findTopByOrderByReportDateDesc();
}

