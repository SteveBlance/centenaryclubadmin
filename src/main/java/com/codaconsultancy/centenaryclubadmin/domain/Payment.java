package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.PaymentMapper;
import com.codaconsultancy.centenaryclubadmin.view.PaymentViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
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
    
    public PaymentViewBean toViewBean() {
        return PaymentMapper.INSTANCE.entityToViewBean(this);
    }
}
