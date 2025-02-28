package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.domain.Prize;
import com.codaconsultancy.centenaryclubadmin.mappers.MemberMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    public Member toEntity() {
        return MemberMapper.INSTANCE.viewBeanToAddressEntity(this);

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
