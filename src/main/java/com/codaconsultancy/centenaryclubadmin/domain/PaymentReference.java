package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
