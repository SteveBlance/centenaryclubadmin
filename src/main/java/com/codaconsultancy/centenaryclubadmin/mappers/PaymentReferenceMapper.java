package com.codaconsultancy.centenaryclubadmin.mappers;

import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.view.PaymentReferenceViewBean;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentReferenceMapper {
    PaymentReferenceMapper INSTANCE = Mappers.getMapper( PaymentReferenceMapper.class );

    PaymentReferenceViewBean entityToViewBean(PaymentReference paymentReference);
    PaymentReference viewBeanToEntity(PaymentReferenceViewBean paymentReferenceViewBean);
}
