package com.codaconsultancy.centenaryclubadmin.controller;

import com.codaconsultancy.centenaryclubadmin.domain.LotteryDraw;
import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.MemberTypeTotal;
import com.codaconsultancy.centenaryclubadmin.domain.Prize;
import com.codaconsultancy.centenaryclubadmin.service.*;
import com.codaconsultancy.centenaryclubadmin.view.MemberAddressViewBean;
import com.codaconsultancy.centenaryclubadmin.view.ReportViewBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ReportsController extends LifelineController {

    private final ReportsService reportsService;

    public ReportsController(SecuritySubjectService securitySubjectService, NotificationService notificationService, ReportsService reportsService, MemberService memberService, LotteryDrawService lotteryDrawService) {
        super(securitySubjectService, notificationService, memberService, lotteryDrawService);
        this.reportsService = reportsService;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public ModelAndView navigateToReports() {
        return modelAndView("reports").addObject("reportConfig", new ReportViewBean());
    }

    @RequestMapping(value = "/memberTypeTotals", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<MemberTypeTotal> helloRest() {
        return reportsService.getMemberTypeTotals();
    }

    @RequestMapping(value = "/export-report", method = RequestMethod.POST, produces = "text/csv")
    @ResponseBody
    public String exportReport(@Valid @ModelAttribute("reportForm") ReportForm reportForm) {
        switch (reportForm.getReportType()) {
            case "All Members":
                return allMembersCSV();
            case "All Prize Draws":
                return allDrawsCSV();
        }
        return allMembersCSV();
    }

    private String allDrawsCSV() {
        List<LotteryDraw> lotteryDraws = lotteryDrawService.fetchAllLotteryDraws();
        StringBuilder drawsCSV = new StringBuilder();
        drawsCSV.append("Lottery date,Draw name,Winners").append("\n");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (LotteryDraw lotteryDraw : lotteryDraws) {
            drawsCSV.append(sdf.format(lotteryDraw.getLotteryDate())).append(",")
                    .append(lotteryDraw.getName()).append(",")
                    .append("\"");
            List<Prize> prizes = lotteryDraw.getPrizes();
            StringBuilder winners = new StringBuilder();
            String prefix = "";
            for (Prize prize : prizes) {
                Member winner = prize.getWinner();
                StringBuilder winnerId = new StringBuilder();
                Long membershipNumber = winner.getMembershipNumber();
                if (winner.isFanbasePayer()) {
                    winnerId.append(membershipNumber).append("/Fanbase-").append(winner.getFanbaseId());
                } else {
                    winnerId.append(membershipNumber);
                }
                winners.append(prefix)
                        .append(prize.getPrize()).append(": ")
                        .append(winner.getForename()).append(" ")
                        .append(winner.getSurname()).append(", ")
                        .append(winner.getAddresses().getFirst().getTown()).append(" (")
                        .append(winnerId).append(")");
                 prefix = ", ";
            }
            drawsCSV.append(winners).append("\"\n");
        }
        return drawsCSV.toString();
    }

    private String allMembersCSV() {
        List<MemberAddressViewBean> allMembers = memberService.findAllMembersWithAddresses();
        StringBuilder memberCSV = new StringBuilder();
        memberCSV.append("Membership number,Forename,Surname,Status,Member no,Payments,Email,Home number,Mobile number,Address, Eligibility").append("\n");
        for (MemberAddressViewBean member : allMembers) {
            memberCSV.append(member.getMembershipNumber()).append(",")
                    .append(member.getForename()).append(",")
                    .append(member.getSurname()).append(",")
                    .append(member.getStatus()).append(",")
                    .append(member.getMembershipType()).append(",")
                    .append(member.getPayerType()).append(",")
                    .append(member.getEmail()).append(",")
                    .append("\"").append(member.getLandlineNumber()).append("\"").append(",")
                    .append("\"").append(member.getMobileNumber()).append("\"").append(",")
                    .append(member.getFullAddress()).append(",")
                    .append(member.isEligibleForDrawStored()? "Eligible for next draw" : "Not eligible for next draw").append("\n");
        }
        return memberCSV.toString();
    }
}
