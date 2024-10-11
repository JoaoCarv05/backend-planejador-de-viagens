package com.planner.plannerEmailService.DTO;
import java.util.List;
import java.util.UUID;

public record ParticipantEmailDTO(List<Participant> participants, UUID trip_id, String trip_destination, String trip_starts_at, String trip_ends_at){}