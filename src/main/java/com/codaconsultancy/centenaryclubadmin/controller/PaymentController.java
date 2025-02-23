package com.codaconsultancy.centenaryclubadmin.controller;

import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.Payment;
import com.codaconsultancy.centenaryclubadmin.domain.PaymentReference;
import com.codaconsultancy.centenaryclubadmin.service.*;
import com.codaconsultancy.centenaryclubadmin.view.PaymentReferenceViewBean;
import com.codaconsultancy.centenaryclubadmin.view.PaymentViewBean;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController extends LifelineController {

    private static final String ENABLED = "enabled";
    private static final String DISABLED = "disabled";
    static final String PAYMENTS_PAGE = "payments";
    static final String MEMBER_PAYMENTS_PAGE = "member-payments";
    static final String PAYMENT_DETAILS_PAGE = "payment";
    static final String ADD_PAYMENT_PAGE = "add-payment";
    static final String EDIT_PAYMENT_PAGE = "edit-payment";
    static final String PAYMENT_REFERENCES_PAGE = "payment-references";
    static final String ADD_PAYMENT_REFERENCE_PAGE = "add-payment-reference";
    static final String UPLOAD_PAYMENTS_PAGE = "upload-payments";
    private static final String CREDITED_ACCOUNT = "82621900174982CA";

    private final PaymentService paymentService;

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    public PaymentController(SecuritySubjectService securitySubjectService, NotificationService notificationService, PaymentService paymentService, MemberService memberService, LotteryDrawService lotteryDrawService) {
        super(securitySubjectService, notificationService, memberService, lotteryDrawService);
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payments/{filter}", method = RequestMethod.GET)
    public ModelAndView navigateToPayments(@PathVariable String filter) {
        List<PaymentViewBean> payments;
        String title;
        String recentTabStatus = ENABLED;
        String matchedTabStatus = ENABLED;
        String unmatchedTabStatus = ENABLED;
        String nonLotteryTabStatus = ENABLED;
        String allTabStatus = ENABLED;
        switch (filter) {
            case "recent":
                payments = paymentService.findRecentPayments();
                title = "Recent payments";
                recentTabStatus = DISABLED;
                break;
            case "matched":
                payments = paymentService.findAllMatchedLotteryPayments();
                title = "Matched payments";
                matchedTabStatus = "disabled";
                break;
            case "unmatched":
                payments = paymentService.findAllUnmatchedPayments();
                title = "Unmatched payments";
                unmatchedTabStatus = DISABLED;
                break;
            case "non-lottery":
                payments = paymentService.findAllNonLotteryPayments();
                title = "Non-lottery payments";
                nonLotteryTabStatus = DISABLED;
                break;
            default:
                payments = paymentService.findAllPayments();
                title = "All payments";
                allTabStatus = DISABLED;
                break;
        }
        return modelAndView(PAYMENTS_PAGE).addObject("payments", payments)
                .addObject("title", title)
                .addObject("recentTabStatus", recentTabStatus)
                .addObject("matchedTabStatus", matchedTabStatus)
                .addObject("unmatchedTabStatus", unmatchedTabStatus)
                .addObject("nonLotteryTabStatus", nonLotteryTabStatus)
                .addObject("allTabStatus", allTabStatus);
    }

    @RequestMapping(value = "payments/member/{membershipNumber}", method = RequestMethod.GET)
    public ModelAndView navigateToPaymentsForMember(@PathVariable Long membershipNumber) {
        Member member = memberService.findMemberByMembershipNumber(membershipNumber);
        List<Payment> payments = paymentService.findPaymentsForMember(member);
        return modelAndView(MEMBER_PAYMENTS_PAGE).addObject("payments", payments).addObject("member", member);
    }

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET)
    public ModelAndView navigateToPaymentDetails(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        return modelAndView(PAYMENT_DETAILS_PAGE).addObject("payment", payment);
    }

    @RequestMapping(value = "/add-payment", method = RequestMethod.GET)
    public ModelAndView navigateToAddPayment() {
        List<Member> members = memberService.findAllMembersOrderedBySurname();
        PaymentViewBean payment = new PaymentViewBean();
        payment.setCreditedAccount(CREDITED_ACCOUNT);
        return modelAndView(ADD_PAYMENT_PAGE).addObject("payment", payment).addObject("members", members);
    }

    @RequestMapping(value = "/edit-payment/{id}", method = RequestMethod.GET)
    public ModelAndView navigateToEditPayment(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        List<Member> possiblePayers = paymentService.findPossiblePayers(payment);
        List<Member> members = memberService.findAllMembersOrderedBySurname();
        PaymentViewBean paymentViewBean = payment.toViewBean();
        return modelAndView(EDIT_PAYMENT_PAGE).addObject("payment", paymentViewBean).addObject("members", members).addObject("possiblePayers", possiblePayers);
    }

    @RequestMapping(value = "/edit-payment", method = RequestMethod.POST)
    public ModelAndView editPayment(@Valid @ModelAttribute("payment") PaymentViewBean paymentViewBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.debug("Validation errors for payment: ", paymentViewBean);
            return navigateToEditPayment(paymentViewBean.getId());
        }
        Member member = memberService.findMemberById(paymentViewBean.getMemberId());
        if (paymentViewBean.isStoreReferenceForMatching() && null != member) {
            PaymentReference paymentReference = new PaymentReference(paymentViewBean.getCreditReference(), paymentViewBean.getName(), true, member);
            paymentService.savePaymentReference(paymentReference);
        }
        Payment payment = paymentViewBean.toEntity();
        payment.setMember(member);

        Payment updatedPayment = paymentService.updatePayment(payment);

        return navigateToPaymentDetails(updatedPayment.getId());
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ModelAndView addPayment(@Valid @ModelAttribute("address") PaymentViewBean paymentViewBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.debug("Validation errors for address: ", paymentViewBean);
            return navigateToAddPayment();
        }
        Payment payment = paymentViewBean.toEntity();
        payment.setLotteryPayment(true);
        Member member = memberService.findMemberById(paymentViewBean.getMemberId());
        payment.setMember(member);
        Payment savedPayment = paymentService.savePayment(payment);
        notificationService.logPayment(1);

        return navigateToPaymentDetails(savedPayment.getId());
    }

    @RequestMapping(value = "/delete-payment/{id}", method = RequestMethod.POST)
    public ModelAndView deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        ModelAndView modelAndView = navigateToPayments("unmatched");
        return addAlertMessage(modelAndView, "success", "Payment deleted");
    }

    @RequestMapping(value = "/mark-payment-non-lottery/{id}", method = RequestMethod.POST)
    public ModelAndView markPaymentAsNonLottery(@PathVariable Long id) {
        paymentService.markPaymentAsNonLottery(id);
        ModelAndView modelAndView = navigateToPaymentDetails(id);
        return addAlertMessage(modelAndView, "success", "Payment marked as non-lottery payment");
    }

    @RequestMapping(value = "/mark-payment-for-lottery/{id}", method = RequestMethod.POST)
    public ModelAndView markPaymentForLottery(@PathVariable Long id) {
        paymentService.markPaymentForLottery(id);
        ModelAndView modelAndView = navigateToPaymentDetails(id);
        return addAlertMessage(modelAndView, "success", "Payment marked as a lottery payment");
    }

    @RequestMapping(value = "/payment-references/member/{number}", method = RequestMethod.GET)
    public ModelAndView navigateToPaymentReferencesForMember(@PathVariable Long number) {
        Member member = memberService.findMemberByMembershipNumber(number);
        return modelAndView(PAYMENT_REFERENCES_PAGE).addObject("member", member);
    }

    @RequestMapping(value = "/add-payment-reference/member/{number}", method = RequestMethod.GET)
    public ModelAndView navigateToAddPaymentReference(@PathVariable Long number) {
        Member member = memberService.findMemberByMembershipNumber(number);
        PaymentReferenceViewBean paymentReferenceViewBean = new PaymentReferenceViewBean();
        paymentReferenceViewBean.setMember(member);
        return modelAndView(ADD_PAYMENT_REFERENCE_PAGE).addObject("paymentReference", paymentReferenceViewBean);
    }

    @RequestMapping(value = "/payment-reference", method = RequestMethod.POST)
    public ModelAndView addPaymentReference(@Valid @ModelAttribute("paymentReference") PaymentReferenceViewBean paymentReferenceViewBean, BindingResult bindingResult) {
        Member member = memberService.findMemberByMembershipNumber(paymentReferenceViewBean.getMember().getMembershipNumber());
        if (bindingResult.hasErrors()) {
            logger.debug("Validation errors for reference: ", paymentReferenceViewBean);
            return navigateToAddPaymentReference(member.getMembershipNumber());
        }
        paymentReferenceViewBean.setIsActive(true);
        paymentReferenceViewBean.setMember(member);
        List<PaymentReference> paymentReferences = member.getPaymentReferences();
        PaymentReference paymentReference = paymentReferenceViewBean.toEntity();
        paymentReferences.add(paymentReference);
        member.setPaymentReferences(paymentReferences);

        paymentService.savePaymentReference(paymentReference);
        return navigateToPaymentReferencesForMember(member.getMembershipNumber());
    }

    @RequestMapping(value = "/upload-payments", method = RequestMethod.GET)
    public ModelAndView navigateToUploadPayments() {
        return modelAndView(UPLOAD_PAYMENTS_PAGE).addObject(DISABLED, true);
    }

    @RequestMapping(value = "/upload-payments", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        List<Payment> parsedPayments = new ArrayList<>();
        String contents;
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
            contents = IOUtils.toString(stream, StandardCharsets.UTF_8);
            parsedPayments = paymentService.parsePayments(contents, filename);
            paymentService.savePayments(parsedPayments);
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            logger.error(e.getMessage());
            ModelAndView modelAndView = modelAndView(PaymentController.UPLOAD_PAYMENTS_PAGE).addObject("payments", parsedPayments).addObject("filename", filename).addObject(DISABLED, true);
            return addAlertMessage(modelAndView, "danger", "Upload of payments failed. Please check bank statement file.");
        }
        notificationService.logPayment(parsedPayments.size());
        return modelAndView(PaymentController.UPLOAD_PAYMENTS_PAGE).addObject("payments", parsedPayments).addObject("filename", filename).addObject(DISABLED, false);
    }

}
