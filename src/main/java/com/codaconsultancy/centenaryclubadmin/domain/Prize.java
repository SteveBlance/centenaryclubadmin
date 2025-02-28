package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "prizes")
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRIZE")
    private String prize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WINNER_ID")
    private Member winner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOTTERY_DRAW_ID")
    private LotteryDraw lotteryDraw;

}
