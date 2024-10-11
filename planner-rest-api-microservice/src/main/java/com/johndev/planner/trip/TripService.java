package com.johndev.planner.trip;

import com.johndev.planner.kafka.DTO.owner_email_dto.OwnerEmailDTO;
import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantDTO;
import com.johndev.planner.activity.*;
import com.johndev.planner.exceptions.exceptions.DateNotValidException;
import com.johndev.planner.exceptions.exceptions.EntityNotFoundException;
import com.johndev.planner.kafka.KafkaProducer;
import com.johndev.planner.link.LinkData;
import com.johndev.planner.link.LinkPayload;
import com.johndev.planner.link.LinkResponse;
import com.johndev.planner.link.LinkService;
import com.johndev.planner.participant.ParticipantIdCreateResponse;
import com.johndev.planner.participant.ParticipantRequestPayload;
import com.johndev.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TripService {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ActivityService activitiesService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private KafkaProducer kafkaProducer;

    public ResponseEntity<TripCreateResponse> createTripService(TripRequestPayload payload) {
        Trip rawTrip = new Trip(payload);
        ZonedDateTime starts = ZonedDateTime.parse(payload.starts_at());
        ZonedDateTime ends = ZonedDateTime.parse(payload.ends_at());
        if (starts.isAfter(ends) || starts.isBefore(ZonedDateTime.now())) {
            throw new DateNotValidException("The start date cannot be in the past and must be earlier than the end date. Please provide a valid date range");
        } else {
            this.tripRepository.save(rawTrip);
            this.participantService.registerParticipantsToEvent(payload.emails_to_invite(), rawTrip);
            kafkaProducer.sendOwnerConfirmEmail(new OwnerEmailDTO(rawTrip));
            return ResponseEntity.ok(new TripCreateResponse(rawTrip.getId()));
        }
    }

    public ResponseEntity<Trip> getTripResponseEntity(UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            return ResponseEntity.ok(trip.get());
        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<Trip> updateTripService(UUID id, TripUpdateRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            ZonedDateTime starts = ZonedDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
            ZonedDateTime ends = ZonedDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
            if (starts.isAfter(ends) || starts.isBefore(ZonedDateTime.now())) {
                throw new DateNotValidException("The start date cannot be in the past and must be earlier than the end date. Please provide a valid date range");
            } else {
                rawTrip.setDestination(payload.destination());
                rawTrip.setStarts_at(starts);
                rawTrip.setEnds_at(ends);
                this.tripRepository.save(rawTrip);
                return ResponseEntity.ok(rawTrip);
            }
        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<Trip> confirmTripService(UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            rawTrip.setIs_confirmed(true);
            this.tripRepository.save(rawTrip);
            this.participantService.TriggerConfirmationEmailToParticipants(rawTrip);
            return ResponseEntity.ok(rawTrip);
        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<ParticipantIdCreateResponse> inviteParticipantService(UUID id, ParticipantRequestPayload participantPayload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip rawTrip = trip.get();

            ParticipantIdCreateResponse participantCreateResponse = this.participantService.registerParticipantToEvent(participantPayload.email(), rawTrip);
            if (rawTrip.getIs_confirmed())
                this.participantService.TriggerConfirmationEmailToParticipant(participantPayload.email());

            return ResponseEntity.ok(participantCreateResponse);
        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<List<ParticipantDTO>> getAllParticipantsService(UUID id) {
        List<ParticipantDTO> participantListResponse = participantService.returnAllTripParticipants(id);
        return ResponseEntity.ok(participantListResponse);
    }

    public ResponseEntity<ActivityResponse> createActivityService(UUID id, ActivityPayload activitiesPayload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip tripResponse = trip.get();
            ZonedDateTime occurs = ZonedDateTime.parse(activitiesPayload.occurs_at(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
            if (occurs.isAfter(tripResponse.getEnds_at()) || occurs.isBefore(tripResponse.getStarts_at())) {
                throw new DateNotValidException("the activity data cannot occur before the trip date or after de trip date.");
            } else {
                Activity activity = activitiesService.saveActivity(activitiesPayload, tripResponse);
                return ResponseEntity.ok(new ActivityResponse(activity.getId()));
            }

        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<List<ActivityData>> returnAllActivitiesService(UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip Rawtrip = trip.get();
            long days = ChronoUnit.DAYS.between(Rawtrip.getStarts_at(), Rawtrip.getEnds_at());
            List<ActivityData> collectedList = IntStream.rangeClosed(0, (int) days).mapToObj(day -> {
                ZonedDateTime date = Rawtrip.getStarts_at().plusDays(day);
                List<Activity> allActivitiesByOccursAt = activitiesService.findAllActivitiesByTripId(id);
                List<Activity> sameDayActivities = allActivitiesByOccursAt.stream().filter(activity -> activity.getOccurs_at().toLocalDate().isEqual(date.toLocalDate())).toList();
                return new ActivityData(date, sameDayActivities);
            }).collect(Collectors.toList());

            return ResponseEntity.ok(collectedList);
        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<LinkResponse> createLinkService(UUID id, LinkPayload linkPayload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            LinkResponse linkResponse = linkService.registerLink(linkPayload, rawTrip);
            return ResponseEntity.ok(linkResponse);
        } else throw new EntityNotFoundException();
    }

    public ResponseEntity<List<LinkData>> getAllLinksService(UUID id) {
        if (tripRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(linkService.findAllTripLinks(id));
        } else throw new EntityNotFoundException();
    }
}
