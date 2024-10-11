package com.johndev.planner.trip;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TripRequestPayload(@Schema(description = "The name of the trip destination", example = "Ubatuba, SP")
                                 @NotBlank
                                 String destination,
                                 @Schema(description = "the date-time notation as defined by RFC 3339, section 5.6, for example, 2025-10-01T12:00:00Z", example = "2025-09-01T12:00:00Z")
                                 String starts_at,
                                 @Schema(description = "the date-time notation as defined by RFC 3339, section 5.6, for example, 2025-10-01T12:00:00Z", example = "2025-10-01T12:00:00Z")
                                 String ends_at,
                                 @Schema(description = "Participant Emails to invite", example = "[\"email1@example.com\", \"email2@example.com\"]")
                                 List<@Email(message = "email must be valid") String> emails_to_invite,
                                 @Schema(description = "the participant email", example = "joao@gmail.com")
                                 @Email
                                 String owner_email,
                                 @Schema(description = "the owner name of trip", example = "João Victor")
                                 @NotBlank
                                 String owner_name) {
}
