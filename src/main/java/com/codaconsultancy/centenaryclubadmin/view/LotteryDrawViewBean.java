package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.LotteryDraw;
import com.codaconsultancy.centenaryclubadmin.domain.Member;
import com.codaconsultancy.centenaryclubadmin.domain.Prize;
import com.codaconsultancy.centenaryclubadmin.mappers.LotteryDrawMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LotteryDrawViewBean {

    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date drawDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lotteryDate;

    private String name;

    @NotNull
    private String drawMaster;

    private Integer numberOfPrizes;

    private List<Prize> prizes = new ArrayList<>();

    public String showPrizesSummary() {
        StringBuilder prizeSummary = new StringBuilder();
        List<Prize> prizes = this.prizes;
        for (Prize prize : prizes) {
            Member winner = prize.getWinner();
            prizeSummary.append(prize.getPrize()).append(": ").append(winner.getForename()).append(" ").append(winner.getSurname());
            if (null != winner.getAddresses().getFirst().getTown() && !winner.getAddresses().getFirst().getTown().isEmpty()) {
                prizeSummary.append(", ").append(winner.getAddresses().getFirst().getTown());
            }
            Long membershipNumber = winner.getMembershipNumber();
            String memberNumberDisplay = String.format("%d", membershipNumber);
            if (winner.getPayerType().equals("Fanbase") && null != winner.getFanbaseId()) {
                memberNumberDisplay = membershipNumber + "/Fanbase-" + winner.getFanbaseId();
            }
            prizeSummary.append(" (").append(memberNumberDisplay).append("), ");
        }
        prizeSummary.deleteCharAt(prizeSummary.length() - 1);
        prizeSummary.deleteCharAt(prizeSummary.length() - 1);
        return prizeSummary.toString();
    }

    public LotteryDraw toEntity() {
        return LotteryDrawMapper.INSTANCE.viewBeanToEntity(this);
    }
}

