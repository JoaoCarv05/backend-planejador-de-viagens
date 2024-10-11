package com.johndev.planner.participant;

import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantEmailDTO;
import com.johndev.planner.kafka.KafkaProducer;
import com.johndev.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantDTO;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    public void registerParticipantsToEvent(List<String> participantsToinvite, Trip trip) {
        List<Participant> participantList = participantsToinvite.stream().map(email -> new Participant(email, trip)).toList();
        participantRepository.saveAll(participantList);
    }

    public ParticipantIdCreateResponse registerParticipantToEvent(String email, Trip trip) {
        Participant participant = new Participant(email, trip);
        participantRepository.save(participant);
        return new ParticipantIdCreateResponse(participant.getId());
    }

    public void TriggerConfirmationEmailToParticipants(Trip trip) {
        List<Participant> participantList = participantRepository.findByTrip_Id(trip.getId());
        List<ParticipantDTO> participantDTOList = participantList.stream().map(participant -> new ParticipantDTO(participant.getId(), participant.getName(), participant.getEmail(), participant.getIs_confirmed())).toList();
        ParticipantEmailDTO participantEmailDTO = new ParticipantEmailDTO(participantDTOList, trip.getId(), trip.getDestination(), trip.getStarts_at().toString(), trip.getEnds_at().toString());
        System.out.println(participantEmailDTO);
        kafkaProducer.sendParticipantConfirmEmail(participantEmailDTO);
    }

    public void TriggerConfirmationEmailToParticipant(String email) {

    }

    public List<ParticipantDTO> returnAllTripParticipants(UUID id) {
        return participantRepository.findByTrip_Id(id).stream().map(participant -> new ParticipantDTO(participant.getId(), participant.getName(), participant.getEmail(), participant.getIs_confirmed())).toList();
    }
}
