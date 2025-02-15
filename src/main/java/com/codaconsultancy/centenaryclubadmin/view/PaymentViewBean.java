package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.mappers.PaymentMapper;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class PaymentViewBean {

    public PaymentViewBean() {
    }

    public PaymentViewBean(Date paymentDate, Float paymentAmount, String creditReference, String creditedAccount, String name, boolean isLotteryPayment) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.creditReference = creditReference;
        this.creditedAccount = creditedAccount;
        this.name = name;
        this.isLotteryPayment = isLotteryPayment;
    }

    public PaymentViewBean(Long id, Date paymentDate, Float paymentAmount, String creditReference, String creditedAccount, String name, boolean isLotteryPayment, Long memberId, String memberDisplayText) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.creditReference = creditReference;
        this.creditedAccount = creditedAccount;
        this.name = name;
        this.isLotteryPayment = isLotteryPayment;
        this.memberId = memberId;
        this.memberDisplayText = memberDisplayText;
    }

    public PaymentViewBean(Long id, Date paymentDate, Float paymentAmount, String creditReference, String creditedAccount, String name, boolean isLotteryPayment, String memberDisplayText) {
        this(id, paymentDate, paymentAmount, creditReference, creditedAccount, name, isLotteryPayment, 0L, memberDisplayText);
    }

    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    @NotNull
    private Float paymentAmount;

    @NotNull
    private String creditReference;

    private String creditedAccount;

    private String name;

    private Long memberId;

    private String memberDisplayText;

    private boolean isLotteryPayment;

    private String comments;

    private boolean storeReferenceForMatching;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCreditedAccount() {
        return creditedAccount;
    }

    public void setCreditedAccount(String creditedAccount) {
        this.creditedAccount = creditedAccount;
    }

    public String getCreditReference() {
        return creditReference;
    }

    public void setCreditReference(String creditReference) {
        this.creditReference = creditReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStoreReferenceForMatching() {
        return storeReferenceForMatching;
    }

    public void setStoreReferenceForMatching(boolean storeReferenceForMatching) {
        this.storeReferenceForMatching = storeReferenceForMatching;
    }

    public boolean isLotteryPayment() {
        return isLotteryPayment;
    }

    public void setLotteryPayment(boolean isLotteryPayment) {
        this.isLotteryPayment = isLotteryPayment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberDisplayText() {
        return memberDisplayText;
    }

    public void setMemberDisplayText(String memberDisplayText) {
        this.memberDisplayText = memberDisplayText;
    }

    public Payment toEntity() {
        return PaymentMapper.INSTANCE.viewBeanToEntity(this);
    }
}

