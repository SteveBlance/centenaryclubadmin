package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.mappers.MemberMapper;
import com.codaconsultancy.centenaryclubadmin.mappers.PaymentReferenceMapper;

public class PaymentReferenceViewBean {

    private Long id;

    private String reference;

    private String name;

    private Boolean isActive;

    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
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

    public PaymentReference toEntity() {
        return PaymentReferenceMapper.INSTANCE.viewBeanToEntity(this);

    }
}
