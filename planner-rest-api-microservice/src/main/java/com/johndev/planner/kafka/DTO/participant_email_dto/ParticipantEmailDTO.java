package com.johndev.planner.kafka.DTO.participant_email_dto;
import lombok.Builder;

import java.util.List;
import java.util.UUID;


public record ParticipantEmailDTO (List<ParticipantDTO> participants, UUID trip_id, String trip_destination, String trip_starts_at, String trip_ends_at){}