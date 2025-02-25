package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.LotteryDrawMapper;
import com.codaconsultancy.centenaryclubadmin.view.LotteryDrawViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lottery_draws")
public class LotteryDraw {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "DRAW_DATE")
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date drawDate;

    @Getter
    @Setter
    @Column(name = "LOTTERY_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lotteryDate;

    @Getter
    @Setter
    @Column(name = "NAME")
    private String name;

    @Getter
    @Setter
    @Column(name = "DRAW_MASTER")
    @NotNull
    private String drawMaster;

    @Getter
    @Setter
    @OneToMany(mappedBy = "lotteryDraw")
    private List<Prize> prizes = new ArrayList<>();

    @Transient
    public String showPrizesSummary() {
        StringBuilder summary = new StringBuilder();
        List<Prize> prizes = this.prizes;
        for (Prize prize : prizes) {
            Member winner = prize.getWinner();
            summary.append(prize.getPrize()).append(": ")
                    .append(winner.getForename()).append(" ").append(winner.getSurname());
            if (null != winner.getAddresses().getFirst().getTown() && !winner.getAddresses().getFirst().getTown().isEmpty()) {
                summary.append(", ").append(winner.getAddresses().getFirst().getTown());
            }
            Long membershipNumber = winner.getMembershipNumber();
            String memberNumberDisplay = String.format("%d", membershipNumber);
            if (winner.getPayerType().equals("Fanbase") && null != winner.getFanbaseId()) {
                memberNumberDisplay = membershipNumber + "/Fanbase-" + winner.getFanbaseId();
            }
            summary.append(" (").append(memberNumberDisplay).append("), ");
        }
        summary.deleteCharAt(summary.length() - 1);
        summary.deleteCharAt(summary.length() - 1);
        return summary.toString();
    }

    public LotteryDrawViewBean toViewBean() {
        return LotteryDrawMapper.INSTANCE.entityToViewBean(this);
    }
}
