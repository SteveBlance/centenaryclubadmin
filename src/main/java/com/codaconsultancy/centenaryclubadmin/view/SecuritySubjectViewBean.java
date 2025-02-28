package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.SecuritySubject;
import com.codaconsultancy.centenaryclubadmin.mappers.SecuritySubjectMapper;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SecuritySubjectViewBean {

    private Long id;

    private String forename;
    private String surname;
    private String username;
    private String previousPassword;
    private String password;
    private String confirmPassword;
    private int failedLoginAttempts;
    private boolean accountLocked;

    public SecuritySubject toEntity() {
        return SecuritySubjectMapper.INSTANCE.viewBeanToEntity(this);
    }
}
