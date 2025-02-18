package com.codaconsultancy.centenaryclubadmin.controller;

import com.codaconsultancy.centenaryclubadmin.domain.Notification;
import com.codaconsultancy.centenaryclubadmin.domain.SecuritySubject;
import com.codaconsultancy.centenaryclubadmin.service.LotteryDrawService;
import com.codaconsultancy.centenaryclubadmin.service.MemberService;
import com.codaconsultancy.centenaryclubadmin.service.NotificationService;
import com.codaconsultancy.centenaryclubadmin.service.SecuritySubjectService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class LifelineController {

    protected final SecuritySubjectService securitySubjectService;
    protected final MemberService memberService;
    protected final LotteryDrawService lotteryDrawService;

    final
    NotificationService notificationService;

    public LifelineController(SecuritySubjectService securitySubjectService, NotificationService notificationService, MemberService memberService, LotteryDrawService lotteryDrawService) {
        this.securitySubjectService = securitySubjectService;
        this.notificationService = notificationService;
        this.memberService = memberService;
        this.lotteryDrawService = lotteryDrawService;
    }

    String loggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    ModelAndView modelAndView(String page) {
        ModelAndView modelAndView;
        List<Notification> notifications = notificationService.fetchLatestNotifications();
        String username = loggedInUser();
        SecuritySubject securitySubject = !username.equals("anonymousUser") ? securitySubjectService.findByUsername(username) : null;
        if (securitySubject != null && securitySubject.isPasswordToBeChanged()) {
            modelAndView = addAlertMessage(new ModelAndView("change-password").addObject("user", securitySubject.toViewBean()), "info", SecuritySubjectService.PASSWORD_RULES_MESSAGE);
        } else {
            modelAndView = new ModelAndView(page).addObject("loggedInUser", username)
                    .addObject("notifications", notifications).addObject("alertMessage", "");
        }
        return modelAndView;
    }


    ModelAndView addAlertMessage(ModelAndView modelAndView, String alertType, String messageText) {
        return modelAndView.addObject("alertClass", "alert alert-" + alertType).addObject("alertMessage", messageText);
    }
}
