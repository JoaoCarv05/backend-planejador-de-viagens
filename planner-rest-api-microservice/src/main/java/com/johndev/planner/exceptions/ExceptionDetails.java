package com.johndev.planner.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ExceptionDetails {
    private Integer code;
    private String message;
    private LocalDateTime timestamp;
}
