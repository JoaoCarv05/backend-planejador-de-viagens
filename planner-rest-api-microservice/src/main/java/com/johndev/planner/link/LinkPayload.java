package com.johndev.planner.link;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LinkPayload(
        @NotBlank(message = "Link cannot be empty.")
        @Schema(description = "the Link Title", example = "Hotel in Rio de Janeiro")
        String title,
        @NotBlank(message = "Url cannot be empty.")
        @Schema(description = "the title url", example = "https://www.airbnb.com.br")
        String url) {
}
