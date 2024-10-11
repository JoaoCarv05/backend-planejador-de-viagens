package com.planner.plannerEmailService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planner.plannerEmailService.DTO.OwnerEmailDTO;
import org.apache.kafka.common.serialization.Deserializer;

public class OwnerEmailDTODeserializer implements Deserializer<OwnerEmailDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OwnerEmailDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, OwnerEmailDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar o objeto", e);
        }
    }
}

