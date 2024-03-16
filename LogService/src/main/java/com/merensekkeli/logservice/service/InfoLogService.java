package com.merensekkeli.logservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merensekkeli.logservice.dao.InfoLogRepository;
import com.merensekkeli.logservice.entity.ErrorLog;
import com.merensekkeli.logservice.entity.InfoLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoLogService {

    private final InfoLogRepository infoLogRepository;

    @KafkaListener(topics = "infoLog", groupId = "log-consumer-group")
    public void consume (String jsonMessage) {
        log.info("consume started!");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> logMessage;
        try {
            logMessage = objectMapper.readValue(jsonMessage, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String message = logMessage.get("message");
        String serviceName = logMessage.get("serviceName");

        InfoLog infoLog = new InfoLog();
        infoLog.setDate(LocalDateTime.now());
        infoLog.setMessage(message);
        infoLog.setServiceName(serviceName);
        infoLog.setDescription("Info Log");
        infoLogRepository.save(infoLog);

        log.info("consume finished!");
    }

    @KafkaListener(topics = "infoLog.DLT", groupId = "log-consumer-group-dlt")
    public void consumeDLT (String message) {
        log.error("Received message from DLT Queue " + message);
    }
}
