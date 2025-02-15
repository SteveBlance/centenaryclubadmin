package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.view.MemberAddressViewBean;
import com.codaconsultancy.centenaryclubadmin.view.MemberViewBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMembershipNumber(Long membershipNumber);

    @Query("select max(m.membershipNumber)+1 from Member m")
    Long nextMembershipNumber();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m ")
    List<MemberViewBean> findAllMembers();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m " +
                    "WHERE m.status = 'Open' ")
    List<MemberViewBean> findCurrentMembers();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberAddressViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId, a.addressLine1, a.addressLine2, a.addressLine3, a.postcode, a.region, a.town) " +
                    "FROM Member m, Address a " +
                    "WHERE m.id = a.member.id ")
    List<MemberAddressViewBean> findAllMembersWithAddresses();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m " +
                    "WHERE m.status = 'Open' " +
                    "AND m.isEligibleForDrawStored = TRUE")
    List<MemberViewBean> findEligibleMembers();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m " +
                    "WHERE m.status IN ('TBC', 'Open')" +
                    "AND m.isEligibleForDrawStored = FALSE")
    List<MemberViewBean> findIneligibleMembers();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m " +
                    "WHERE m.status = 'Open'" +
                    "AND m.isEligibleForDrawStored = FALSE")
    List<MemberViewBean> findLapsedMembers();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m " +
                    "WHERE m.status = 'TBC' ")
    List<MemberViewBean> findPendingMembers();

    @Query(value =
            "SELECT new com.codaconsultancy.centenaryclubadmin.view.MemberViewBean(m.id, m.membershipNumber, m.forename, m.surname, m.membershipType, m.status, m.payerType, m.joinDate, m.leaveDate, m.email, m.landlineNumber, m.mobileNumber, m.isEligibleForDrawStored, m.emailOptOut, m.fanbaseId) " +
                    "FROM Member m " +
                    "WHERE m.status = 'Closed' ")
    List<MemberViewBean> findFormerMembers();

    List<Member> findAllByOrderBySurnameAscForenameAsc();

    List<Member> findAllByStatusOrderBySurnameAscForenameAsc(String status);

    List<Member> findAllBySurnameIgnoreCaseAndStatusOrderByForename(String surname, String status);

    Long countByStatus(String status);
}
