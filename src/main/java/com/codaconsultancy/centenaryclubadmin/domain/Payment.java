package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.PaymentMapper;
import com.codaconsultancy.centenaryclubadmin.view.PaymentViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Getter
    @Setter
    @NotNull
    @Column(name = "PAYMENT_AMOUNT")
    private Float paymentAmount;

    @Getter
    @Setter
    @NotNull
    @Column(name = "CREDIT_REFERENCE")
    private String creditReference;

    @Getter
    @Setter
    @Column(name = "CREDITED_ACCOUNT")
    private String creditedAccount;

    @Getter
    @Setter
    @Column(name = "NAME")
    private String name;

    @Getter
    @Setter
    @Column(name = "IS_LOTTERY_PAYMENT")
    private boolean isLotteryPayment;

    @Getter
    @Setter
    @Column(name = "COMMENTS")
    private String comments;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    
    public PaymentViewBean toViewBean() {
        return PaymentMapper.INSTANCE.entityToViewBean(this);
    }
}
