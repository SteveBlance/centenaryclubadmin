package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.SecuritySubjectMapper;
import com.codaconsultancy.centenaryclubadmin.view.SecuritySubjectViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public boolean isPasswordToBeChanged() {
        return passwordToBeChanged;
    }

    public void setPasswordToBeChanged(boolean passwordToBeChanged) {
        this.passwordToBeChanged = passwordToBeChanged;
    }

    public SecuritySubjectViewBean toViewBean() {
        return SecuritySubjectMapper.INSTANCE.entityToViewBean(this);
    }
}
