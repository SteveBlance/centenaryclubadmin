package com.codaconsultancy.centenaryclubadmin.mappers;

import com.codaconsultancy.centenaryclubadmin.domain.Address;
import com.codaconsultancy.centenaryclubadmin.view.AddressViewBean;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    AddressViewBean entityToViewBean(Address address);
    Address viewBeanToEntity(AddressViewBean addressViewBean);
}
