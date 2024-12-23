package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class UpdateSLSPConsumer {

    @Inject
    SanPhamService sanPhamService;
    Logger log = LoggerFactory.getLogger(UpdateSLSPConsumer.class);
    ObjectMapper mapper = new ObjectMapper();
    // This method will be called when a message is consumed from Kafka
    @Incoming("order-events") // This is the Kafka topic you're consuming from
    public void consume(String jsonMessage) throws JsonProcessingException {
        // Deserialize the JSON message to List<UpdateSLSPDTO>
        log.info("Consume message = {}", jsonMessage);
//        List<UpdateSLSPDTO> updates = jsonb.fromJson(jsonMessage, List.class);
        UpdateSLSPDTOKaf updateSLSPDTOKaf = mapper.readValue(jsonMessage, UpdateSLSPDTOKaf.class);
        // Process the updates
       sanPhamService.updateSoLuongSanPham(updateSLSPDTOKaf.getUpdates(), updateSLSPDTOKaf.operation);
       log.info("Update quantity successfuly");
    }
}
