package com.codaconsultancy.centenaryclubadmin.mappers;

import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.view.PaymentViewBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper( PaymentMapper.class );

    @Mapping(source = "member.id", target = "memberId")
    PaymentViewBean entityToViewBean(Payment payment);

    @Mapping(source = "memberId", target = "member.id")
    Payment viewBeanToEntity(PaymentViewBean paymentViewBean);
}
