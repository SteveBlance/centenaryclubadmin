package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.LotteryDrawMapper;
import com.codaconsultancy.centenaryclubadmin.view.LotteryDrawViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lottery_draws")
public class LotteryDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DRAW_DATE")
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date drawDate;

    @Column(name = "LOTTERY_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lotteryDate;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DRAW_MASTER")
    @NotNull
    private String drawMaster;

    @OneToMany(mappedBy = "lotteryDraw")
    private List<Prize> prizes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    public Date getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(Date lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrawMaster() {
        return drawMaster;
    }

    public void setDrawMaster(String drawMaster) {
        this.drawMaster = drawMaster;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    @Transient
    public String showPrizesSummary() {
        StringBuilder summary = new StringBuilder();
        List<Prize> prizes = this.prizes;
        for (Prize prize : prizes) {
            Member winner = prize.getWinner();
            summary.append(prize.getPrize()).append(": ")
                    .append(winner.getForename()).append(" ").append(winner.getSurname());
            if (null != winner.getAddresses().get(0).getTown() && !winner.getAddresses().get(0).getTown().isEmpty()) {
                summary.append(", ").append(winner.getAddresses().get(0).getTown());
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
