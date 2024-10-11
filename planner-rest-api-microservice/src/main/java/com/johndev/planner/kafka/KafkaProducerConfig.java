package com.johndev.planner.kafka;
import com.johndev.planner.kafka.DTO.owner_email_dto.OwnerEmailDTO;
import com.johndev.planner.kafka.DTO.owner_email_dto.OwnerEmailSerializer;
import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantEmailDTO;
import com.johndev.planner.kafka.DTO.participant_email_dto.ParticipantEmailSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, ParticipantEmailDTO> participantProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ParticipantEmailSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, ParticipantEmailDTO> participantKafkaTemplate() {
        return new KafkaTemplate<>(participantProducerFactory());
    }

    @Bean
    public ProducerFactory<String, OwnerEmailDTO> ownerProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OwnerEmailSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, OwnerEmailDTO> ownerKafkaTemplate() {
        return new KafkaTemplate<>(ownerProducerFactory());
    }

}
