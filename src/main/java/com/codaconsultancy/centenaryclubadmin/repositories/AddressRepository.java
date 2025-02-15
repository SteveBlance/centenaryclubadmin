package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.Address;
import com.codaconsultancy.centenaryclubadmin.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByMember(Member member);

    List<Address> findByMemberAndIsActive(Member member, boolean isActive);
}
