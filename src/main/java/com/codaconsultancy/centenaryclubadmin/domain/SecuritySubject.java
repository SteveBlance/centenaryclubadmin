package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.SecuritySubjectMapper;
import com.codaconsultancy.centenaryclubadmin.view.SecuritySubjectViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "security_subjects")
public class SecuritySubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "FORENAME")
    private String forename;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    public int failedLoginAttempts;

    @NotNull
    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked;

    @NotNull
    @Column(name = "PASSWORD_TO_BE_CHANGED")
    private boolean passwordToBeChanged;

    public SecuritySubjectViewBean toViewBean() {
        return SecuritySubjectMapper.INSTANCE.entityToViewBean(this);
    }
}
