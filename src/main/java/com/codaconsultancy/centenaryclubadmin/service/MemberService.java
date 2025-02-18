package com.codaconsultancy.centenaryclubadmin.service;

import com.codaconsultancy.centenaryclubadmin.domain.Configuration;
import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.repositories.MemberRepository;
import com.codaconsultancy.centenaryclubadmin.repositories.PaymentRepository;
import com.codaconsultancy.centenaryclubadmin.view.MemberAddressViewBean;
import com.codaconsultancy.centenaryclubadmin.view.MemberViewBean;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberService extends LifelineService {

    private static final String MONTHLY = "Monthly";
    private static final String QUARTERLY = "Quarterly";
    private static final String ANNUAL = "Annual";
    private static final String LIFELINE = "Lifeline";
    private static final int PAYMENT_GRACE_PERIOD_IN_DAYS = 30;
    private static final String TBC_STATUS = "TBC";
    private static final String OPEN_STATUS = "Open";
    private static final String CLOSED_STATUS = "Closed";
    public static final String FANBASE = "Fanbase";

    private final MemberRepository memberRepository;

    private final PaymentRepository paymentRepository;

    public MemberService(MemberRepository memberRepository, PaymentRepository paymentRepository) {
        this.memberRepository = memberRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<MemberViewBean> findAllMembers() {
        return memberRepository.findAllMembers();
    }

    public List<MemberViewBean> findCurrentMembers() {
        return memberRepository.findCurrentMembers();
    }

    public List<MemberAddressViewBean> findAllMembersWithAddresses() {
        return memberRepository.findAllMembersWithAddresses();
    }

    public List<MemberViewBean> findEligibleMembers() {
        return memberRepository.findEligibleMembers();
    }

    public List<MemberViewBean> findIneligibleMembers() {
        return memberRepository.findIneligibleMembers();
    }

    public List<MemberViewBean> findPendingMembers() {
        return memberRepository.findPendingMembers();
    }

    public List<MemberViewBean> findFormerMembers() {
        return memberRepository.findFormerMembers();
    }

    public List<MemberViewBean> findRecentlyLapsedMembers() {
        return memberRepository.findLapsedMembers();
    }

    public List<Member> findAllMembersOrderedBySurname() {
        return memberRepository.findAllByOrderBySurnameAscForenameAsc();
    }

    public Long countAllCurrentMembers() {
        return memberRepository.countByStatus(OPEN_STATUS);
    }

    public Member findMemberByMembershipNumber(Long memberNumber) {
        return memberRepository.findByMembershipNumber(memberNumber);
    }

    public Member saveMember(Member member) {
        if (member.getPayerType().equalsIgnoreCase("Fanbase") && null != member.getFanbaseId()) {
            member.setStatus(OPEN_STATUS);
        } else {
            member.setStatus(TBC_STATUS);
        }
        member.setJoinDate(DateTime.now().toDate());

        return memberRepository.save(member);
    }

    public Long getNextMembershipNumber() {
        return memberRepository.nextMembershipNumber();
    }

    public Member updateMember(Member member) {
        if (TBC_STATUS.equals(member.getStatus()) && member.getPayerType().equalsIgnoreCase(FANBASE) && null != member.getFanbaseId()) {
            member.setStatus(OPEN_STATUS);
        }
        Member storedMember = memberRepository.findById(member.getId()).orElse(null);
        if (OPEN_STATUS.equals(storedMember.getStatus()) && CLOSED_STATUS.equals(member.getStatus())) {
            member.setLeaveDate(DateTime.now().toDate());
        }
        return memberRepository.save(member);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public List<MemberViewBean> fetchMemberDrawEntries() {
        List<MemberViewBean> memberDrawEntries = new ArrayList<>();
        List<MemberViewBean> currentMembers = findCurrentMembers();
        for (MemberViewBean member : currentMembers) {
            if (member.isEligibleForDrawStored()) {
                if (isLifelineMember(member)) {
                    // 3 entries for lifeline members
                    memberDrawEntries.add(member);
                    memberDrawEntries.add(member);
                    memberDrawEntries.add(member);
                } else {
                    // 1 entry for Legacy member
                    memberDrawEntries.add(member);
                }
            }
        }
        return memberDrawEntries;
    }

    public int closeLapsedMemberships() {
        int accountsClosed = 0;
        List<MemberViewBean> currentMembers = memberRepository.findCurrentMembers();
        Member member;
        for (MemberViewBean currentMember : currentMembers) {
            if (!paymentsAreUpToDate(currentMember, PAYMENT_GRACE_PERIOD_IN_DAYS)) {
                member = memberRepository.findById(currentMember.getId()).orElse(null);
                member.setStatus(CLOSED_STATUS);
                updateMember(member);
                accountsClosed++;
            }
        }
        return accountsClosed;
    }

    private boolean isLifelineMember(MemberViewBean member) {
        return member.getMembershipType().equalsIgnoreCase(LIFELINE);
    }

    public boolean isEligibleForDraw(MemberViewBean member) {
        return OPEN_STATUS.equals(member.getStatus()) && (isFanbasePayer(member) || (paymentsAreUpToDate(member, PAYMENT_GRACE_PERIOD_IN_DAYS)));
    }

    private boolean isFanbasePayer(MemberViewBean member) {
        return member.hasFanbaseId() && member.getPayerType().equalsIgnoreCase("Fanbase");
    }

    private boolean paymentsAreUpToDate(MemberViewBean member, int gracePeriodInDays) {
        String payerType = member.getPayerType();
        String membershipType = member.getMembershipType();
        DateTime today = new DateTime();

        Date lastExpectedPaymentDate = getLastExpectedPaymentDate(today, payerType).minus(Period.days(gracePeriodInDays)).toDate();

        Double paymentTotalThisPeriod = paymentRepository.getTotalLotteryPaymentSince(lastExpectedPaymentDate, member.getId());

        return (paymentTotalThisPeriod >= requiredPaymentFrom(payerType, membershipType));
    }

    private Double requiredPaymentFrom(String payerType, String membershipType) {
        Double requiredPayment;
        if (LIFELINE.equals(membershipType)) {
            switch (payerType) {
                case MONTHLY:
                    requiredPayment = 20.00D;
                    break;
                case QUARTERLY:
                    requiredPayment = 60.00D;
                    break;
                case ANNUAL:
                    requiredPayment = 240.00D;
                    break;
                default:
                    requiredPayment = 20.00D;
                    break;
            }
        } else {
            switch (payerType) {
                case MONTHLY:
                    requiredPayment = 8.66D;
                    break;
                case QUARTERLY:
                    requiredPayment = 26.00D;
                    break;
                case ANNUAL:
                    requiredPayment = 104.00D;
                    break;
                default:
                    requiredPayment = 8.66D;
                    break;
            }
        }
        return requiredPayment;
    }

    DateTime getLastExpectedPaymentDate(DateTime fromDate, String payerType) {
        DateTime lastExpectedPaymentDate;
        switch (payerType) {
            case MONTHLY:
                lastExpectedPaymentDate = fromDate.minus(Period.months(1));
                break;
            case QUARTERLY:
                lastExpectedPaymentDate = fromDate.minus(Period.months(3));
                break;
            case ANNUAL:
                lastExpectedPaymentDate = fromDate.minus(Period.years(1));
                break;
            default:
                lastExpectedPaymentDate = fromDate.minus(Period.months(1));
                break;
        }
        return lastExpectedPaymentDate;
    }

    // Every hour
    @Scheduled(cron = "0 0 * * * *")
    public void scheduledUpdateEligibilityCall() {
        updateEligibilityStatuses();
    }

    public void updateEligibilityStatuses() {
        if (lotteryEligibilityStatusRefreshRequired()) {
            logMessage("Updating Eligibility Statuses");
            List<MemberViewBean> members = memberRepository.findCurrentMembers();
            for (MemberViewBean member : members) {
                updateEligibilityStatus(member);
            }
            Configuration lastRefresh = configurationRepository.findByName(LAST_ELIGIBILITY_REFRESH_DATE);
            lastRefresh.setDateValue(DateTime.now().toDate());
            Configuration refreshRequired = configurationRepository.findByName(ELIGIBILITY_REFRESH_REQUIRED);
            refreshRequired.setBooleanValue(false);
            configurationRepository.save(lastRefresh);
            configurationRepository.save(refreshRequired);
        }
    }

    public void updateEligibilityStatus(MemberViewBean member) {
        boolean wasPreviouslyEligible = member.isEligibleForDrawStored();
        boolean isNowEligible = isEligibleForDraw(member);
        if (isNowEligible) {
            member.setEligibleForDrawStored(true);
            if (!wasPreviouslyEligible) {
                updateMember(member.toEntity());
            }
        } else {
            wasPreviouslyEligible = member.isEligibleForDrawStored();
            member.setEligibleForDrawStored(false);
            if (wasPreviouslyEligible) {
                updateMember(member.toEntity());
            }
        }
    }

    public boolean lotteryEligibilityStatusRefreshRequired() {
        Configuration byName = configurationRepository.findByName(LAST_ELIGIBILITY_REFRESH_DATE);
        Date lastRefresh = byName.getDateValue();
        boolean isRefreshRequired = configurationRepository.findByName(ELIGIBILITY_REFRESH_REQUIRED).getBooleanValue();
        DateTime now = DateTime.now();
        DateTime lastRefreshDate = new DateTime(lastRefresh);
        Duration durationSinceLastRefresh = new Duration(lastRefreshDate, now);
        return (durationSinceLastRefresh.getStandardHours() >= 24) || isRefreshRequired;
    }
}
