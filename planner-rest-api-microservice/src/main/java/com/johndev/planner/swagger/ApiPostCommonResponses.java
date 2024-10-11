package com.johndev.planner.swagger;

import com.johndev.planner.exceptions.ExceptionDetails;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid data type.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionDetails.class))),
        @ApiResponse(responseCode = "404", description = "Trip not found.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionDetails.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionDetails.class)))

})
public @interface ApiPostCommonResponses {
    String responseCode() default "200";

    String description() default "Success";

    Class<?> implementation() default Void.class;
}
