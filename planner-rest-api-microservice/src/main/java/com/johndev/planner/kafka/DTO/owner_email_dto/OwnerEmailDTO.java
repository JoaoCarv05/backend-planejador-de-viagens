package com.johndev.planner.kafka.DTO.owner_email_dto;


import com.johndev.planner.trip.Trip;

import java.util.UUID;

public record OwnerEmailDTO(String owner_email, UUID trip_id, String owner_name, String trip_destination, String trip_starts_at, String trip_ends_at) {
    public OwnerEmailDTO(Trip trip) {
        this(trip.getOwner_email(), trip.getId(), trip.getOwner_name(), trip.getDestination(), trip.getStarts_at().toString(), trip.getEnds_at().toString());
    }
}
