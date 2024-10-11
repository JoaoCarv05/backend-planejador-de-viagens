package com.johndev.planner.link;

import com.johndev.planner.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Link")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(name = "title")
    String title;
    @Column(name = "url")
    String url;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    Trip trip;

    public Link(LinkPayload linkPayload, Trip trip) {
        this.title = linkPayload.title();
        this.url = linkPayload.url();
        this.trip = trip;
    }
}
