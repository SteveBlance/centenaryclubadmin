package com.codaconsultancy.centenaryclubadmin.controller;

import com.codaconsultancy.centenaryclubadmin.domain.SecuritySubject;
import com.codaconsultancy.centenaryclubadmin.exceptions.SubjectPasswordIncorrectException;
import com.codaconsultancy.centenaryclubadmin.exceptions.SubjectUsernameExistsException;
import com.codaconsultancy.centenaryclubadmin.service.LotteryDrawService;
import com.codaconsultancy.centenaryclubadmin.service.MemberService;
import com.codaconsultancy.centenaryclubadmin.service.NotificationService;
import com.codaconsultancy.centenaryclubadmin.service.SecuritySubjectService;
import com.codaconsultancy.centenaryclubadmin.view.SecuritySubjectViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController extends LifelineController {

    public static final String ADMINISTRATORS_PAGE = "administrators";
    public static final String ADMINISTRATOR_PAGE = "administrator";
    public static final String ADD_ADMINISTRATOR_PAGE = "add-administrator";
    public static final String EDIT_ADMINISTRATOR_PAGE = "edit-administrator";
    public static final String CHANGE_PASSWORD_PAGE = "change-password";
    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    public AdminController(SecuritySubjectService securitySubjectService, NotificationService notificationService, MemberService memberService, LotteryDrawService lotteryDrawService) {
        super(securitySubjectService, notificationService, memberService, lotteryDrawService);
    }


    @RequestMapping(value = "/administrators", method = RequestMethod.GET)
    public ModelAndView navigateToAdministrators() {
        List<SecuritySubject> administrators = securitySubjectService.findAllSecuritySubjects();

        return modelAndView(ADMINISTRATORS_PAGE).addObject("administrators", administrators);
    }

    @RequestMapping(value = "/administrator", method = RequestMethod.GET)
    public ModelAndView navigateToAdministrator() {
        SecuritySubject administrator = securitySubjectService.findByUsername(loggedInUser());
        return modelAndView(ADMINISTRATOR_PAGE).addObject("administrator", administrator);
    }

    @RequestMapping(value = "/add-administrator", method = RequestMethod.GET)
    public ModelAndView navigateToAddAdministrator() {
        SecuritySubjectViewBean administrator = new SecuritySubjectViewBean();
        return addAlertMessage(new ModelAndView(ADD_ADMINISTRATOR_PAGE).addObject("administrator", administrator), "info", SecuritySubjectService.PASSWORD_RULES_MESSAGE);
    }

    @RequestMapping(value = "/edit-administrator", method = RequestMethod.GET)
    public ModelAndView navigateToEditAdministrator() {
        SecuritySubject administrator = securitySubjectService.findByUsername(loggedInUser());
        return modelAndView(EDIT_ADMINISTRATOR_PAGE).addObject("administrator", administrator);
    }

    @RequestMapping(value = "/edit-administrator", method = RequestMethod.POST)
    public ModelAndView editMember(@Valid @ModelAttribute("administrator") SecuritySubject administrator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOG.debug("Validation errors for administrator: ", administrator);
            return navigateToEditAdministrator();
        }
        securitySubjectService.updateSecuritySubject(administrator);
        return navigateToAdministrator();
    }
    @RequestMapping(value = "/administrator", method = RequestMethod.POST)
    public ModelAndView addNewAdministrator(@Valid @ModelAttribute("subject") SecuritySubjectViewBean securitySubjectViewBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOG.debug("Validation errors for securitySubject: ", securitySubjectViewBean);
            return modelAndView(ADD_ADMINISTRATOR_PAGE).addObject("administrator", securitySubjectViewBean);
        }
        try {
            securitySubjectService.registerNewSecuritySubject(securitySubjectViewBean);
        } catch (SubjectUsernameExistsException | SubjectPasswordIncorrectException e) {
            ModelAndView modelAndView = modelAndView(ADD_ADMINISTRATOR_PAGE).addObject("administrator", securitySubjectViewBean);
            return addAlertMessage(modelAndView, "danger", e.getMessage());
        }

        return navigateToAdministrators();
    }

    @RequestMapping(value = "/change-password/{username}", method = RequestMethod.GET)
    public ModelAndView navigateToChangePassword(@PathVariable String username) {
        SecuritySubject securitySubject = securitySubjectService.findByUsername(username);
        return modelAndView(CHANGE_PASSWORD_PAGE).addObject("user", securitySubject.toViewBean());
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ModelAndView changePassword(@Valid @ModelAttribute("user") SecuritySubjectViewBean securitySubjectViewBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOG.debug("Validation errors for securitySubject: ", securitySubjectViewBean);
            return navigateToChangePassword(securitySubjectViewBean.getUsername());
        }
        try {
            securitySubjectService.updatePassword(securitySubjectViewBean);
        } catch (SubjectPasswordIncorrectException e) {
            return addAlertMessage(navigateToChangePassword(securitySubjectViewBean.getUsername()), "danger", e.getMessage());
        }

        return modelAndView("index").addObject("lastDraw" , lotteryDrawService.fetchLastDraw());
    }

    @EventListener
    public void authenticationSuccess(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        securitySubjectService.registerSuccessfulLogin(user.getUsername());
    }

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        securitySubjectService.registerFailedLoginAttempt(username);
    }
}
