package com.johndev.planner.trip;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TripUpdateRequestPayload(@NotBlank
                                       @Schema(description = "the new Trip destination", example = "São Paulo, SP")
                                       String destination,
                                       @Schema(description = "the end date of the trip", example = "2025-09-01T12:00:00Z")
                                       String starts_at,
                                       @Schema(description = "the end date of the trip", example = "2025-10-01T12:00:00Z")
                                       String ends_at) {
}
