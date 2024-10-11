package com.planner.plannerEmailService.participant_email;

import com.planner.plannerEmailService.DTO.ParticipantEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class participantsEmailsToInviteListener {

    @Autowired
    private ParticipantEmailSender emailSender;

    @KafkaListener(topics = "participants-emails-to-confirm", groupId = "participant", containerFactory = "participantkafkaListenerContainerFactory")
    public void sendEmail(ParticipantEmailDTO mensagem) {
        emailSender.sendEmail(mensagem);
    }
}
