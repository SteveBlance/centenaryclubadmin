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

@Entity
@Table(name = "members")
public class Member {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull()
    @Column(name = "MEMBERSHIP_NUMBER")
    private Long membershipNumber;

    @Setter
    @Getter
    @NotNull
    @Column(name = "MEMBERSHIP_TYPE")
    private String membershipType;

    @Setter
    @Getter
    @NotNull
    @Column(name = "STATUS")
    private String status;

    @Setter
    @Getter
    @NotNull
    @Column(name = "FORENAME")
    private String forename;

    @Setter
    @Getter
    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @Setter
    @Getter
    @NotNull
    @Column(name = "PAYER_TYPE")
    private String payerType;

    @Setter
    @Getter
    @Column(name = "JOIN_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date joinDate;

    @Setter
    @Getter
    @Column(name = "LEAVE_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date leaveDate;

    @Setter
    @Getter
    @Column(name = "COMMENTS")
    private String comments;

    @Setter
    @Getter
    @Column(name = "EMAIL")
    private String email;

    @Setter
    @Getter
    @Column(name = "LANDLINE_NUMBER")
    private String landlineNumber;

    @Setter
    @Getter
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Setter
    @Getter
    @Column(name = "CARD_REQUEST_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cardRequestDate;

    @Setter
    @Getter
    @Column(name = "CARD_ISSUED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date cardIssuedDate;

    @Setter
    @Getter
    @Column(name = "WELCOME_LETTER_ISSUED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date welcomeLetterIssuedDate;

    @Setter
    @Getter
    @OneToMany(mappedBy = "member")
    private List<Address> addresses = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "winner")
    private List<Prize> prizeWins = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "member")
    private List<PaymentReference> paymentReferences = new ArrayList<>();

    @Column(name = "IS_ELIGIBLE_FOR_DRAW")
    private boolean isEligibleForDrawStored;

    @Setter
    @Getter
    @Column(name = "EMAIL_OPT_OUT")
    private boolean emailOptOut;

    @Setter
    @Getter
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
