package com.johndev.planner.kafka;

import com.johndev.planner.kafka.DTO.owner_email_dto.OwnerEmailDTO;
import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

    private final KafkaTemplate<String, ParticipantEmailDTO> participantkafkaTemplate;
    private final KafkaTemplate<String, OwnerEmailDTO> ownerKafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, ParticipantEmailDTO> participantkafkaTemplate, KafkaTemplate<String, OwnerEmailDTO> ownerKafkaTemplate) {
        this.participantkafkaTemplate = participantkafkaTemplate;
        this.ownerKafkaTemplate = ownerKafkaTemplate;
    }

    public void sendParticipantConfirmEmail(ParticipantEmailDTO participantEmailDTO) {
        participantkafkaTemplate.send("participants-emails-to-confirm", participantEmailDTO);
    }

    public void sendOwnerConfirmEmail(OwnerEmailDTO ownerEmailDTO) {
        ownerKafkaTemplate.send("owner-emails-to-confirm", ownerEmailDTO);
    }

}
