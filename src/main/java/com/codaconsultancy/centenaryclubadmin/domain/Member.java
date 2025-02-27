package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.MemberMapper;
import com.codaconsultancy.centenaryclubadmin.view.MemberViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    @Column(name = "MEMBERSHIP_NUMBER")
    private Long membershipNumber;

    @NotNull
    @Column(name = "MEMBERSHIP_TYPE")
    private String membershipType;

    @NotNull
    @Column(name = "STATUS")
    private String status;

    @NotNull
    @Column(name = "FORENAME")
    private String forename;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "PAYER_TYPE")
    private String payerType;

    @Column(name = "JOIN_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date joinDate;

    @Column(name = "LEAVE_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date leaveDate;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LANDLINE_NUMBER")
    private String landlineNumber;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "CARD_REQUEST_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cardRequestDate;

    @Column(name = "CARD_ISSUED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cardIssuedDate;

    @Column(name = "WELCOME_LETTER_ISSUED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date welcomeLetterIssuedDate;

    @OneToMany(mappedBy = "member")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "winner")
    private List<Prize> prizeWins = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PaymentReference> paymentReferences = new ArrayList<>();

    @Column(name = "IS_ELIGIBLE_FOR_DRAW")
    private boolean isEligibleForDrawStored;

    @Column(name = "EMAIL_OPT_OUT")
    private boolean emailOptOut;

    @Column(name = "FANBASE_ID")
    private Integer fanbaseId;

    public boolean isEligibleForDrawStored() {
        return isEligibleForDrawStored;
    }

    public void setEligibleForDrawStored(boolean isEligibleForDrawStored) {
        this.isEligibleForDrawStored = isEligibleForDrawStored;
    }

    @Transient
    public boolean hasFanbaseId() {
        return null != this.fanbaseId;
    }

    @Transient
    public boolean isFanbasePayer() {
        return this.payerType.equals("Fanbase") && hasFanbaseId();
    }

    public MemberViewBean toViewBean() {
        return MemberMapper.INSTANCE.entityToViewBean(this);
    }

}
