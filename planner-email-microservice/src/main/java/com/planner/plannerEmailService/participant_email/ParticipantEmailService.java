package com.planner.plannerEmailService.participant_email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantEmailService {
    @Autowired
    private ParticipantEmailRepository emailRepository;
    public void saveEmail(ParticipantEmailModel email){
        emailRepository.save(email);
    }
}
