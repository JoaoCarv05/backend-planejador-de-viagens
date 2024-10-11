package com.johndev.planner.participant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ParticipantRequestPayload(@NotBlank(message = "Name cannot be empty.")
                                        @Schema(description = "the participant name", example = "pietro")
                                        String name,
                                        @Email(message = "must be a valid Email")
                                        @Schema(description = "the participant email", example = "pietro@gmail.com")
                                        String email) {
}
