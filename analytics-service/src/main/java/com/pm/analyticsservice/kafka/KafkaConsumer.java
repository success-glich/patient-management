package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import patients.events.PatientEvent;

import java.nio.charset.StandardCharsets;

@Service
public class KafkaConsumer {
    private static final Logger log = LogManager.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(
//            topics = "patient",
//            groupId = "analytics-service",
//            containerFactory = "kafkaListenerContainerFactory"
//    )
//    public void consumeEvent(@Payload(required = false) byte[] data) {
//        if (data == null) {
//            log.warn("Received null message");
//            return;
//        }
//
//        try {
//            PatientEvent patientEvent = PatientEvent.parseFrom(data);
//            log.info("PatientEvent received: [PatientId={}, Name={}, Email={}, EventType={}]",
//                    patientEvent.getPatientId(),
//                    patientEvent.getName(),
//                    patientEvent.getEmail(),
//                    patientEvent.getEventType());
//        } catch (InvalidProtocolBufferException e) {
//            log.error("Error deserializing event: {}", new String(data, StandardCharsets.UTF_8), e);
//        }
//    }

    @KafkaListener(topics = "${kafka.topic.patient:patient}", groupId = "analytics-group")
    public void listen(byte[] data) {
        try {
//            Pa.Patient p = PatientProto.Patient.parseFrom(data);
            System.out.println("data: " + data);
            PatientEvent p = PatientEvent.parseFrom(data);
            // now you have a deserialized Patient object
            System.out.println("Analytics service received patient proto => id: " + p.getPatientId()
                    + ", name: " + p.getName() + ", email: " + p.getEmail()
                    + ", email: " + p.getEmail() + ", event type: " + p.getEventType());

            // TODO: run analytics logic, save to DB, call another service, metrics, etc.
            // Example: increment counters, push to elasticsearch, aggregate, etc.

        } catch (Exception ex) {
            System.err.println("Failed to parse Patient proto: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}