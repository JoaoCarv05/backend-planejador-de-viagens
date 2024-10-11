package com.planner.plannerEmailService.participant_email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantEmailRepository extends JpaRepository<ParticipantEmailModel, UUID> {
}
