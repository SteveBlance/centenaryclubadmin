package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.mappers.MemberMapper;
import com.codaconsultancy.centenaryclubadmin.mappers.PaymentReferenceMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentReferenceViewBean {

    private Long id;
    private String reference;
    private String name;
    private Boolean isActive;
    private Member member;

    public PaymentReference toEntity() {
        return PaymentReferenceMapper.INSTANCE.viewBeanToEntity(this);
    }
}
