package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentReferenceRepository extends JpaRepository<PaymentReference, Long> {

    List<PaymentReference> findByMember(Member member);

    List<PaymentReference> findByMemberAndIsActive(Member member, boolean isActive);
}
