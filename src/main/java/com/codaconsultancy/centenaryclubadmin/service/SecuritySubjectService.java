package com.codaconsultancy.centenaryclubadmin.service;

import com.codaconsultancy.centenaryclubadmin.domain.SecuritySubject;
import com.codaconsultancy.centenaryclubadmin.exceptions.SubjectPasswordIncorrectException;
import com.codaconsultancy.centenaryclubadmin.exceptions.SubjectUsernameExistsException;
import com.codaconsultancy.centenaryclubadmin.repositories.SecuritySubjectRepository;
import com.codaconsultancy.centenaryclubadmin.view.SecuritySubjectViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SecuritySubjectService extends LifelineService implements UserDetailsService {

    public static final String PASSWORD_RULES_MESSAGE = "Password must be between 8 and 100 characters and must contain uppercase characters, lowercase characters and numbers";
    private static final String BLACKLISTED_WORD_MESSAGES = "Password contains a disallowed word or number. e.g. Easily guessed words like your name, username or Pars related words etc.";
    private static final String PASSWORD_AND_CONFIRMATION_MISMATCH_MESSAGE = "Password and Confirmation don't match";
    private static final String PASSWORD_MUST_CHANGE_MESSAGE = "The New Password must not be the same as the Current Password";

    private SecuritySubjectRepository securitySubjectRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setSecuritySubjectRepository(SecuritySubjectRepository securitySubjectRepository) {
        this.securitySubjectRepository = securitySubjectRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<SecuritySubject> findAllSecuritySubjects() {
        return securitySubjectRepository.findAll();
    }

    public SecuritySubject registerNewSecuritySubject(SecuritySubjectViewBean securitySubjectViewBean) throws SubjectUsernameExistsException, SubjectPasswordIncorrectException {
        String enteredNewPassword = securitySubjectViewBean.getPassword();
        String enteredNewPasswordConfirmation = securitySubjectViewBean.getConfirmPassword();
        if (usernameExists(securitySubjectViewBean.getUsername())) {
            throw new SubjectUsernameExistsException("Username: '" + securitySubjectViewBean.getUsername() + "' already exists");
        }
        if (!enteredNewPassword.equals(enteredNewPasswordConfirmation)) {
            throw new SubjectPasswordIncorrectException(PASSWORD_AND_CONFIRMATION_MISMATCH_MESSAGE);
        }
        if (!passwordRulesMet(securitySubjectViewBean.getPassword())) {
            throw new SubjectPasswordIncorrectException(PASSWORD_RULES_MESSAGE);
        }
        if (containsBlacklistedWord(securitySubjectViewBean, enteredNewPassword)) {
            throw new SubjectPasswordIncorrectException(BLACKLISTED_WORD_MESSAGES);
        }
        SecuritySubject securitySubject = new SecuritySubject();
        securitySubject.setForename(securitySubjectViewBean.getForename());
        securitySubject.setSurname(securitySubjectViewBean.getSurname());
        securitySubject.setUsername(securitySubjectViewBean.getUsername());
        securitySubject.setPasswordToBeChanged(true);
        securitySubject.setPassword(passwordEncoder.encode(securitySubjectViewBean.getPassword()));

        return securitySubjectRepository.save(securitySubject);
    }

    private boolean usernameExists(String username) {
        return securitySubjectRepository.countByUsername(username) != 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        SecuritySubject securitySubject = securitySubjectRepository.findByUsername(username);
        if (securitySubject == null) {
            return null;
        }

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String password = securitySubject.getPassword();
        boolean accountNonLocked = !securitySubject.isAccountLocked();
        UserDetails user = new User(
                username, password, true, true, true, accountNonLocked, auth);
        return user;
    }

    public void registerFailedLoginAttempt(String username) {
        SecuritySubject securitySubject = securitySubjectRepository.findByUsername(username);
        int failedLoginAttempts = securitySubject.getFailedLoginAttempts() + 1;
        logMessage("Failed login attempt (" + failedLoginAttempts + ") by user '" + username + "'");
        securitySubject.setFailedLoginAttempts(failedLoginAttempts);
        if (failedLoginAttempts == 4) {
            securitySubject.setAccountLocked(true);
        }
        securitySubjectRepository.save(securitySubject);
    }

    public void registerSuccessfulLogin(String username) {
        logMessage("User '" + username + "' logged in successfully");
        SecuritySubject securitySubject = securitySubjectRepository.findByUsername(username);
        int failedLoginAttempts = securitySubject.getFailedLoginAttempts();
        if (failedLoginAttempts > 0) {
            securitySubject.setFailedLoginAttempts(0);
            securitySubjectRepository.save(securitySubject);
        }
    }

    public void updatePassword(SecuritySubjectViewBean securitySubjectViewBean) throws SubjectPasswordIncorrectException {
        SecuritySubject securitySubject = securitySubjectRepository.getReferenceById(securitySubjectViewBean.getId());
        String enteredCurrentPassword = securitySubjectViewBean.getPreviousPassword();
        String storedCurrentPassword = securitySubject.getPassword();
        String enteredNewPassword = securitySubjectViewBean.getPassword();
        String enteredNewPasswordConfirmation = securitySubjectViewBean.getConfirmPassword();
        if (!passwordEncoder.matches(enteredCurrentPassword, storedCurrentPassword)) {
            throw new SubjectPasswordIncorrectException("The Current Password entered is incorrect");
        }
        if (passwordEncoder.matches(enteredNewPassword, storedCurrentPassword)) {
            throw new SubjectPasswordIncorrectException(PASSWORD_MUST_CHANGE_MESSAGE);
        }
        if (!enteredNewPassword.equals(enteredNewPasswordConfirmation)) {
            throw new SubjectPasswordIncorrectException(PASSWORD_AND_CONFIRMATION_MISMATCH_MESSAGE);
        }
        if (!passwordRulesMet(enteredNewPassword)) {
            throw new SubjectPasswordIncorrectException(PASSWORD_RULES_MESSAGE);
        }
        if (containsBlacklistedWord(securitySubjectViewBean, enteredNewPassword)) {
            throw new SubjectPasswordIncorrectException(BLACKLISTED_WORD_MESSAGES);
        }
        securitySubject.setPassword(passwordEncoder.encode(securitySubjectViewBean.getPassword()));
        securitySubject.setPasswordToBeChanged(false);
        securitySubjectRepository.save(securitySubject);
    }

    public SecuritySubject findByUsername(String username) {
        return securitySubjectRepository.findByUsername(username);
    }

    private boolean passwordRulesMet(String password) {
        //Between 8 and 100 characters. Must be a mixture of uppercase characters, lowercase characters and numbers.
        StringBuilder patternBuilder = new StringBuilder();
        patternBuilder.append("((?=.*[a-z])");
        patternBuilder.append("(?=.*[A-Z])");
        patternBuilder.append("(?=.*[0-9])");
        patternBuilder.append(".{8,100})");
        String pattern = patternBuilder.toString();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        boolean passwordMatches = m.matches();
        return passwordMatches;
    }

    private boolean containsBlacklistedWord(SecuritySubjectViewBean securitySubjectViewBean, String password) {
        String[] blacklistedWords = {"password", "dafc", "d.a.f.c", "pars", "dunfermline", "1885", "eastend", "kozma", "cclifeline", "lifeline", "centenary", "football", "qwerty"};
        String lowerCasePassword = password.toLowerCase();
        for (String blacklistedWord : blacklistedWords) {
            if (lowerCasePassword.contains(blacklistedWord)) {
                return true;
            }
        }
        if (lowerCasePassword.contains(securitySubjectViewBean.getUsername().toLowerCase()) ||
                lowerCasePassword.contains(securitySubjectViewBean.getForename().toLowerCase()) ||
                lowerCasePassword.contains(securitySubjectViewBean.getSurname().toLowerCase())) {
            return true;
        }
        return false;
    }

    public SecuritySubject updateSecuritySubject(SecuritySubject administrator) {
        return securitySubjectRepository.save(administrator);
    }
}