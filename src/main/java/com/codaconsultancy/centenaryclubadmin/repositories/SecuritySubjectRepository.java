package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.SecuritySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuritySubjectRepository extends JpaRepository<SecuritySubject, Long> {

    SecuritySubject findByUsername(String username);

    int countByUsername(String username);
}
