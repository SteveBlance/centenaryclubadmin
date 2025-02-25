package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_references")
public class PaymentReference {

    public PaymentReference() {
    }

    public PaymentReference(String reference, String name, Boolean isActive, Member member) {
        this.reference = reference;
        this.name = name;
        this.isActive = isActive;
        this.member = member;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "REFERENCE")
    private String reference;

    @Getter
    @Setter
    @Column(name = "NAME")
    private String name;

    @Getter
    @Setter
    @NotNull
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
