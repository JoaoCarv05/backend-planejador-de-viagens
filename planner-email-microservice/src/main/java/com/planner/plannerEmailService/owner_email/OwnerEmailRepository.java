package com.planner.plannerEmailService.owner_email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerEmailRepository extends JpaRepository<OwnerEmailModel, UUID> {
}