package com.planner.plannerEmailService.owner_email;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "owner_confirmation_email")
public class OwnerEmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "email_from")
    private String emailFrom;
    @Column(name = "email_to")
    private String emailTo;
    @Column(name = "subject")
    private String subject;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @Column(name = "date_email")
    private ZonedDateTime dateEmail;
}