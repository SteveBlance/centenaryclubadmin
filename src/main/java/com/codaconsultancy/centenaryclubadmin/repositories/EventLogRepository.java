package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {
}

