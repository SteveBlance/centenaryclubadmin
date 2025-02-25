package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.SecuritySubjectMapper;
import com.codaconsultancy.centenaryclubadmin.view.SecuritySubjectViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "security_subjects")
public class SecuritySubject {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "FORENAME")
    private String forename;

    @Getter
    @Setter
    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @Getter
    @Setter
    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @Getter
    @Setter
    @NotNull
    @Size(min = 8, max = 100)
    @Column(name = "PASSWORD")
    private String password;

    @Getter
    @Setter
    @NotNull
    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    public int failedLoginAttempts;

    @Getter
    @Setter
    @NotNull
    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked;

    @Getter
    @Setter
    @NotNull
    @Column(name = "PASSWORD_TO_BE_CHANGED")
    private boolean passwordToBeChanged;

    public SecuritySubjectViewBean toViewBean() {
        return SecuritySubjectMapper.INSTANCE.entityToViewBean(this);
    }
}
