package com.merensekkeli.companyservice.general;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String serviceName, String message) {
        Map<String, String> logMessage = new HashMap<>();
        logMessage.put("serviceName", serviceName);
        logMessage.put("message", message);

        String jsonMessage;
        try {
            jsonMessage = new ObjectMapper().writeValueAsString(logMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(topic, jsonMessage);

    }
}
