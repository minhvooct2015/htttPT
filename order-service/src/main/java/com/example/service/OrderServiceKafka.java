package com.example.service;


import com.example.DTOS.UpdateSLSPDTO;
import com.example.DTOS.UpdateSLSPDTOKaf;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
        import jakarta.enterprise.context.ApplicationScoped;
        import org.eclipse.microprofile.reactive.messaging.Channel;
        import org.eclipse.microprofile.reactive.messaging.Emitter;

        import jakarta.inject.Inject;
import org.slf4j.Logger;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderServiceKafka {

    @Inject
    @Channel("order-events")
    Emitter<String> emitter;

    private final ObjectMapper objectMapper = new ObjectMapper();
    public void sendOrder(UpdateSLSPDTOKaf updateSLSPDTOList) {
        UUID id = UUID.randomUUID();
        try {
            String message = objectMapper.writeValueAsString(updateSLSPDTOList);
            emitter.send(KafkaRecord.of(id.toString(), message));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}

