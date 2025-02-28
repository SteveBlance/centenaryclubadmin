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

@Getter
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

    public Member toEntity() {
        return MemberMapper.INSTANCE.viewBeanToEntity(this);
    }

    public boolean hasFanbaseId() {
        return null != this.fanbaseId;
    }

    public boolean isFanbasePayer() {
        return this.payerType.equals("Fanbase") && hasFanbaseId();
    }
}
