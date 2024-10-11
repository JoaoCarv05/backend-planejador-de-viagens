package com.planner.plannerEmailService.owner_email;

import com.planner.plannerEmailService.owner_email.OwnerEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerEmailService {
    @Autowired
    private OwnerEmailRepository ownerEmailRepository;
    public void saveEmail(OwnerEmailModel ownerEmailModel){
        ownerEmailRepository.save(ownerEmailModel);
    }
}