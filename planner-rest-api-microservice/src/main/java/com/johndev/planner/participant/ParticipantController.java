package com.johndev.planner.participant;

import com.johndev.planner.swagger.ApiPostCommonResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participant")
@CrossOrigin(origins = "*")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @Operation(summary = "Confirm the participant.", tags = {"Participants"})
    @ApiPostCommonResponses(description = "Participant confirmed successfully")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody
    ParticipantRequestPayload participantRequestPayload) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isPresent()) {
            Participant rawParticipant = participant.get();
            rawParticipant.setIs_confirmed(true);
            rawParticipant.setName(participantRequestPayload.name());
            participantRepository.save(rawParticipant);
            return ResponseEntity.ok(rawParticipant);
        }
        return ResponseEntity.notFound().build();
    }
}
