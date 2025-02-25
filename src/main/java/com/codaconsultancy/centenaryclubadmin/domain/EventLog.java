package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "event_log")
public class EventLog {

    public EventLog() {
    }

    public EventLog(String message) {
        this.message = message;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "MESSAGE")
    private String message;

}
