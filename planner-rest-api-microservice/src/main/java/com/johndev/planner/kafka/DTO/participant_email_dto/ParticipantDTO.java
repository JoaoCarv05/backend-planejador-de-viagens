package com.johndev.planner.kafka.DTO.participant_email_dto;

import java.util.UUID;

public record ParticipantDTO(UUID id, String name, String email, Boolean is_confirmed) {}
