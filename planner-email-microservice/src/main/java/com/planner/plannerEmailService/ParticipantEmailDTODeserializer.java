package com.planner.plannerEmailService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planner.plannerEmailService.DTO.ParticipantEmailDTO;
import org.apache.kafka.common.serialization.Deserializer;

public class ParticipantEmailDTODeserializer implements Deserializer<ParticipantEmailDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ParticipantEmailDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, ParticipantEmailDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar o objeto", e);
        }
    }
}

