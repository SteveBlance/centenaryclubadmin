package com.codaconsultancy.centenaryclubadmin.mappers;

import com.codaconsultancy.centenaryclubadmin.domain.SecuritySubject;
import com.codaconsultancy.centenaryclubadmin.view.SecuritySubjectViewBean;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SecuritySubjectMapper {
    SecuritySubjectMapper INSTANCE = Mappers.getMapper( SecuritySubjectMapper.class );

    SecuritySubjectViewBean entityToViewBean(SecuritySubject securitySubject);
    SecuritySubject viewBeanToEntity(SecuritySubjectViewBean securitySubjectViewBean);
}
