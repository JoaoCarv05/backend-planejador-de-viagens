package com.planner.plannerEmailService.owner_email;

import com.planner.plannerEmailService.DTO.OwnerEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OwnerEmailsListener {

    @Autowired
    private OwnerEmailSender emailSender;

    @KafkaListener(topics = "owner-emails-to-confirm", groupId = "owner", containerFactory = "ownerKafkaListenerContainerFactory")
    public void sendEmail(OwnerEmailDTO mensagem) {
        emailSender.sendEmail(mensagem);
    }
}
