package com.codaconsultancy.centenaryclubadmin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.ocpsoft.pretty.time.PrettyTime;


import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notification {

    public Notification() {
    }

    public Notification(Date eventDate, String eventType, String description) {
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "EVENT_DATE")
    private Date eventDate;


    @NotNull
    @Column(name = "EVENT_TYPE")
    private String eventType;

    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String getPrettyTime() {
        String prettyTime = new PrettyTime().format(eventDate);
        DateTime time = new DateTime(eventDate);
        if (time.dayOfYear().equals(DateTime.now().dayOfYear())) {
            prettyTime = "Today";
        }
        return prettyTime;
    }

    @Transient
    public String getFontAwesomeIcon() {
        String iconText;
        switch (eventType) {
            case "Announcement":
                iconText = "<i class=\"fa fa-bullhorn\" aria-hidden=\"true\"></i>";
                break;
            case "Draw":
                iconText = "<i class=\"fa fa-ticket\" aria-hidden=\"true\"></i>";
                break;
            case "NewMember":
                iconText = "<i class=\"fa fa-user\" aria-hidden=\"true\"></i>";
                break;
            case "Twitter":
                iconText = "<i class=\"fa fa-twitter\" aria-hidden=\"true\"></i>";
                break;
            case "Facebook":
                iconText = "<i class=\"fa fa-facebook-official\" aria-hidden=\"true\"></i>";
                break;
            case "Payment":
                iconText = "<i class=\"fa fa-money\" aria-hidden=\"true\"></i>";
                break;
            case "Warning":
                iconText = "<i class=\"fa fa-exclamation-triangle\" aria-hidden=\"true\"></i>";
                break;
            default:
                iconText = "<i class=\"fa fa-comment fa-fw\"></i>";
        }
        return iconText;
    }

}
