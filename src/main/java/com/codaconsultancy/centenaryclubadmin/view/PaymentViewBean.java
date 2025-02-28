package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.mappers.PaymentMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
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

    public Payment toEntity() {
        return PaymentMapper.INSTANCE.viewBeanToEntity(this);
    }
}

