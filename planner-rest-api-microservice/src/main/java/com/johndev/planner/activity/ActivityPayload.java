package com.johndev.planner.activity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ActivityPayload(@NotBlank(message = "O titulo não pode estar em branco")
                              @Schema(description = "the Link Title", example = "Hotel in Rio de Janeiro")
                              String title,
                              @Schema(description = "the end date of the trip", example = "2025-10-01T12:00:00")
                              String occurs_at
) {
}
