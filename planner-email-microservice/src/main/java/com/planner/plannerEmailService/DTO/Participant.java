package com.planner.plannerEmailService.DTO;

import java.util.UUID;

public record Participant(UUID id, String name, String email, Boolean is_confirmed) {}
