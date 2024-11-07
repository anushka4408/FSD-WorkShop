package com.anushka.fsdproject.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sponsors")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sponsId;

    @Column(name = "spons_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal sponsAmount;

    @Column(name = "contact_person", length = 255, nullable = false)
    private String contactPerson;

    @Column(name = "spons_email", length = 255)
    private String sponsEmail;

    // Getters and Setters
    public Long getSponsId() {
        return sponsId;
    }

    public void setSponsId(Long sponsId) {
        this.sponsId = sponsId;
    }

    public BigDecimal getSponsAmount() {
        return sponsAmount;
    }

    public void setSponsAmount(BigDecimal sponsAmount) {
        this.sponsAmount = sponsAmount;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getSponsEmail() {
        return sponsEmail;
    }

    public void setSponsEmail(String sponsEmail) {
        this.sponsEmail = sponsEmail;
    }
}
