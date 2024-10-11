package com.planner.plannerEmailService.DTO;

import java.util.UUID;

public record OwnerEmailDTO(String owner_email, UUID trip_id, String owner_name, String trip_destination, String trip_starts_at, String trip_ends_at) {
}
