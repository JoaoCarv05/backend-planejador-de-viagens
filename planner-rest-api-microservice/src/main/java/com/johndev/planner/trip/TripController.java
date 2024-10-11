package com.johndev.planner.trip;

import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantDTO;
import com.johndev.planner.activity.ActivityData;
import com.johndev.planner.activity.ActivityPayload;
import com.johndev.planner.activity.ActivityResponse;
import com.johndev.planner.link.*;
import com.johndev.planner.participant.*;
import com.johndev.planner.swagger.ApiGetCommonResponses;
import com.johndev.planner.swagger.ApiPostCommonResponses;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
@CrossOrigin(origins = "*")
public class TripController {

    @Autowired
    private TripService tripService;

    @Operation(summary = "Create a new trip.", tags = {"Trips"})
    @ApiPostCommonResponses(description = "Trip Created Successfully.")
    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@Valid @RequestBody TripRequestPayload payload) {
        return tripService.createTripService(payload);
    }

    @Operation(summary = "Get the Trip.", tags = {"Trips"})
    @ApiGetCommonResponses(description = "Successfully retrieved the trip details.")
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable @ParameterObject UUID id) {
        return tripService.getTripResponseEntity(id);
    }

    @Operation(summary = "Update the Trip information.", tags = {"Trips"})
    @ApiPostCommonResponses(description = "Trip updated Successfully.")
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable @ParameterObject UUID id, @Valid @RequestBody TripUpdateRequestPayload payload) {
        return tripService.updateTripService(id, payload);
    }

    @Operation(summary = "Confirm the trip and get the trip.", tags = {"Trips"})
    @ApiGetCommonResponses(description = "Trip successfully confirmed.")
    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable @ParameterObject UUID id) {
        return tripService.confirmTripService(id);
    }

    @Operation(summary = "Invite a new participant to the trip.", tags = {"Participants"})
    @ApiPostCommonResponses(description = "Participant invited Successfully.")
    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantIdCreateResponse> inviteParticipant(@PathVariable @ParameterObject UUID id, @Valid @RequestBody ParticipantRequestPayload participantPayload) {
        return tripService.inviteParticipantService(id, participantPayload);
    }

    @Operation(summary = "Get All participants by its trip id.", tags = {"Participants"})
    @ApiGetCommonResponses(description = "Successfully retrieved the participants.")
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantDTO>> getAllParticipants(@PathVariable @ParameterObject UUID id) {
        return tripService.getAllParticipantsService(id);
    }

    @Operation(summary = "Registry activity by its trip id", tags = {"Activities"})
    @ApiPostCommonResponses(description = "Activity registered Successfully.")
    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registryActivity(@PathVariable @ParameterObject UUID id, @Valid @RequestBody ActivityPayload activitiesPayload) {
        return tripService.createActivityService(id, activitiesPayload);
    }

    @Operation(summary = "Get a List of activities by its trip id", tags = {"Activities"})
    @ApiGetCommonResponses(description = "Successfully retrieved the activities.")
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable @ParameterObject UUID id) {
        return tripService.returnAllActivitiesService(id);
    }

    @Operation(summary = "Registry Link by its trip id", tags = {"Links"})
    @ApiPostCommonResponses(description = "Link Created Successfully.")
    @PostMapping("{id}/link")
    public ResponseEntity<LinkResponse> registryLink(@PathVariable @ParameterObject UUID id, @Valid @RequestBody LinkPayload linkPayload) {
        return tripService.createLinkService(id, linkPayload);
    }

    @Operation(summary = "Get All Trip Links by its trip id", tags = {"Links"})
    @ApiGetCommonResponses(description = "Successfully retrieved the links.")
    @GetMapping("{id}/link")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable @ParameterObject UUID id) {
        return tripService.getAllLinksService(id);
    }
}