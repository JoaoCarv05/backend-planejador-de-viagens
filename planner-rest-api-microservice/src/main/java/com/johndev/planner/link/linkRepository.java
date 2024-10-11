package com.johndev.planner.link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface linkRepository extends JpaRepository<Link,UUID> {
    public List<Link> findByTrip_id(UUID id);
}
