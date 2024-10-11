package com.planner.plannerEmailService.participant_email;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "participant_confirmation_email")
public class ParticipantEmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "participant_id")
    private UUID participantID;
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
