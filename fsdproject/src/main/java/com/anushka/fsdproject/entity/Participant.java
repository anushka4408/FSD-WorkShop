package com.anushka.fsdproject.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    @Column(name = "participant_name", nullable = false)
    private String participantName;

    @Column(name = "participant_phone")
    private String participantPhone;

    @Column(name = "participant_email")
    private String participantEmail;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date regDate;

    // Getters and Setters
    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantPhone() {
        return participantPhone;
    }

    public void setParticipantPhone(String participantPhone) {
        this.participantPhone = participantPhone;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
