package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.domain.Prize;
import com.codaconsultancy.centenaryclubadmin.mappers.MemberMapper;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class MemberAddressViewBean {

    public MemberAddressViewBean() {
    }

    public MemberAddressViewBean(Long id, Long membershipNumber, String forename, String surname, String membershipType, String status, String payerType, Date joinDate, Date leaveDate, String email, String landlineNumber, String mobileNumber, boolean isEligibleForDrawStored, boolean emailOptOut, Integer fanbaseId, String addressLine1, String addressLine2, String addressLine3, String postcode, String region, String town) {
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
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.town = town;
        this.region = region;
        this.postcode = postcode;
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
    private List<Prize> prizeWins;
    private Payment lastPayment;
    private List<PaymentReference> paymentReferences;
    private boolean emailOptOut;
    private Integer fanbaseId;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String postcode;
    private String region;
    private String town;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(Long membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPayerType() {
        return payerType;
    }

    public void setPayerType(String payerType) {
        this.payerType = payerType;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public void setPrizeWins(List<Prize> prizeWins) {
        this.prizeWins = prizeWins;
    }

    public List<Prize> getPrizeWins() {
        return prizeWins;
    }

    public List<PaymentReference> getPaymentReferences() {
        return paymentReferences;
    }

    public void setPaymentReferences(List<PaymentReference> paymentReferences) {
        this.paymentReferences = paymentReferences;
    }

    public void setLastPayment(Payment lastPayment) {
        this.lastPayment = lastPayment;
    }

    public Payment getLastPayment() {
        return lastPayment;
    }

    public Member toEntity() {
        return MemberMapper.INSTANCE.viewBeanToAddressEntity(this);

    }

    public boolean isEligibleForDrawStored() {
        return isEligibleForDrawStored;
    }

    public void setIsEligibleForDrawStored(boolean isEligibleForDrawStored) {
        this.isEligibleForDrawStored = isEligibleForDrawStored;
    }

    public void setEligibleForDrawStored(boolean isEligibleForDrawStored) {
        this.isEligibleForDrawStored = isEligibleForDrawStored;
    }

    public boolean isEmailOptOut() {
        return emailOptOut;
    }

    public void setEmailOptOut(boolean emailOptOut) {
        this.emailOptOut = emailOptOut;
    }

    public Integer getFanbaseId() {
        return fanbaseId;
    }

    public void setFanbaseId(Integer fanbaseId) {
        this.fanbaseId = fanbaseId;
    }

    public String getFullAddress() {
        StringBuilder addr = new StringBuilder("\"");
        if (null != this.addressLine1 && !this.addressLine1.isEmpty()) {addr.append(this.addressLine1).append(", ");}
        if (null != this.addressLine2 && !this.addressLine2.isEmpty()) {addr.append(this.addressLine2).append(", ");}
        if (null != this.addressLine3 && !this.addressLine3.isEmpty()) {addr.append(this.addressLine3).append(", ");}
        if (null != this.town && !this.town.isEmpty()) {addr.append(this.town).append(", ");}
        if (null != this.region && !this.region.isEmpty()) {addr.append(this.region).append(", ");}
        if (null != this.postcode && !this.postcode.isEmpty()) {addr.append(this.postcode);}
        addr.append("\"");
        return addr.toString();
    }
}
