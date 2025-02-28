package com.codaconsultancy.centenaryclubadmin.mappers;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.view.MemberAddressViewBean;
import com.codaconsultancy.centenaryclubadmin.view.MemberViewBean;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper( MemberMapper.class );

    MemberViewBean entityToViewBean(Member member);
    Member viewBeanToEntity(MemberViewBean memberViewBean);
    Member viewBeanToAddressEntity(MemberAddressViewBean memberAddressViewBean);
}
