package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "prizes")
public class Prize {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PRIZE")
    private String prize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WINNER_ID")
    private Member winner;

    @Column(name = "PRIZE_COLLECTED")
    private boolean prizeCollected;

    @Column(name = "PRIZE_COLLECTED_DATE")
    private Date prizeCollectedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOTTERY_DRAW_ID")
    private LotteryDraw lotteryDraw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Member getWinner() {
        return winner;
    }

    public void setWinner(Member winner) {
        this.winner = winner;
    }

    public boolean isPrizeCollected() {
        return prizeCollected;
    }

    public void setPrizeCollected(boolean prizeCollected) {
        this.prizeCollected = prizeCollected;
    }

    public Date getPrizeCollectedDate() {
        return prizeCollectedDate;
    }

    public void setPrizeCollectedDate(Date prizeCollectedDate) {
        this.prizeCollectedDate = prizeCollectedDate;
    }

    public LotteryDraw getLotteryDraw() {
        return lotteryDraw;
    }

    public void setLotteryDraw(LotteryDraw lotteryDraw) {
        this.lotteryDraw = lotteryDraw;
    }
}
