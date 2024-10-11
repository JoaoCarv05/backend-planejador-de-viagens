package com.johndev.planner.activity;
import com.johndev.planner.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(name = "title" , nullable = false)
    String title;

    @Column(name = "occurs_at", nullable = false)
    ZonedDateTime occurs_at;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    Trip trip;

    public Activity(String title, ZonedDateTime occursAt, Trip trip) {
        this.title = title;
        this.occurs_at = occursAt;
        this.trip = trip;
    }

    public Activity(ActivityPayload activitiesPayload, Trip trip) {
        this.title = activitiesPayload.title();
        this.occurs_at = ZonedDateTime.parse(activitiesPayload.occurs_at(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
        this.trip = trip;
    }
}