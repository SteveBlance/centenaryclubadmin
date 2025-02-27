package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.domain.Prize;
import com.codaconsultancy.centenaryclubadmin.mappers.MemberMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
public class MemberViewBean {

    public MemberViewBean() {
    }

    public MemberViewBean(Long id, Long membershipNumber, String forename, String surname, String membershipType, String status, String payerType, Date joinDate, Date leaveDate, String email, String landlineNumber, String mobileNumber, boolean isEligibleForDrawStored, boolean emailOptOut, Integer fanbaseId) {
        this.id = id;
        this.membershipNumber = membershipNumber;
        this.forename = forename;
        this.surname = surname;
        this.membershipType = membershipType;
        this.status = status;
        this.payerType = payerType;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
        this.email = email;
        this.landlineNumber = landlineNumber;
        this.mobileNumber = mobileNumber;
        this.isEligibleForDrawStored = isEligibleForDrawStored;
        this.emailOptOut = emailOptOut;
        this.fanbaseId = fanbaseId;
    }

    private Long id;

    private Long membershipNumber;

    @NotNull
    private String membershipType;

    private String status;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    @NotNull
    private String payerType;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date joinDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date leaveDate;

    private String comments;

    private String email;

    private String landlineNumber;

    private String mobileNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cardRequestDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cardIssuedDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date welcomeLetterIssuedDate;

    private boolean isEligibleForDrawStored;

    private List<AddressViewBean> addresses = new ArrayList<>();
    private List<Prize> prizeWins;
    private Payment lastPayment;
    private List<PaymentReference> paymentReferences;
    private boolean emailOptOut;
    private Integer fanbaseId;

    public Long getId() {
        return id;
    }

    public Long getMembershipNumber() {
        return membershipNumber;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getStatus() {
        return status;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getPayerType() {
        return payerType;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public String getComments() {
        return comments;
    }

    public String getEmail() {
        return email;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Date getCardRequestDate() {
        return cardRequestDate;
    }

    public void setCardRequestDate(Date cardRequestDate) {
        this.cardRequestDate = cardRequestDate;
    }

    public Date getCardIssuedDate() {
        return cardIssuedDate;
    }

    public void setCardIssuedDate(Date cardIssuedDate) {
        this.cardIssuedDate = cardIssuedDate;
    }

    public Date getWelcomeLetterIssuedDate() {
        return welcomeLetterIssuedDate;
    }

    public void setWelcomeLetterIssuedDate(Date welcomeLetterIssuedDate) {
        this.welcomeLetterIssuedDate = welcomeLetterIssuedDate;
    }

    public List<AddressViewBean> getAddresses() {
        return addresses;
    }

    public List<Prize> getPrizeWins() {
        return prizeWins;
    }

    public List<PaymentReference> getPaymentReferences() {
        return paymentReferences;
    }

    public Payment getLastPayment() {
        return lastPayment;
    }

    public Member toEntity() {
        return MemberMapper.INSTANCE.viewBeanToEntity(this);
    }

    public boolean isEligibleForDrawStored() {
        return isEligibleForDrawStored;
    }

    public void setEligibleForDrawStored(boolean isEligibleForDrawStored) {
        this.isEligibleForDrawStored = isEligibleForDrawStored;
    }

    public boolean isEmailOptOut() {
        return emailOptOut;
    }

    public Integer getFanbaseId() {
        return fanbaseId;
    }

    public boolean hasFanbaseId() {
        return null != this.fanbaseId;
    }

    public boolean isFanbasePayer() {
        return this.payerType.equals("Fanbase") && hasFanbaseId();
    }
}
