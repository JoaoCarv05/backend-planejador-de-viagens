package com.johndev.planner.kafka.DTO.participant_email_dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class ParticipantEmailSerializer implements Serializer<ParticipantEmailDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, ParticipantEmailDTO data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar o objeto", e);
        }
    }
}
