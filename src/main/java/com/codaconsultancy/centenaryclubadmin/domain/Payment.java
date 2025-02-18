package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.PaymentMapper;
import com.codaconsultancy.centenaryclubadmin.view.PaymentViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    public Payment() {
    }

    public Payment(Date paymentDate, Float paymentAmount, String creditReference, String creditedAccount, String name, boolean isLotteryPayment) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.creditReference = creditReference;
        this.creditedAccount = creditedAccount;
        this.name = name;
        this.isLotteryPayment = isLotteryPayment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @NotNull
    @Column(name = "PAYMENT_AMOUNT")
    private Float paymentAmount;

    @NotNull
    @Column(name = "CREDIT_REFERENCE")
    private String creditReference;

    @Column(name = "CREDITED_ACCOUNT")
    private String creditedAccount;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_LOTTERY_PAYMENT")
    private boolean isLotteryPayment;

    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public PaymentViewBean toViewBean() {
        return PaymentMapper.INSTANCE.entityToViewBean(this);
    }
}
