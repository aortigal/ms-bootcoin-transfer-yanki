package com.bank.msbootcointransferyankibatch.consumer;

import com.bank.msbootcointransferyankibatch.models.documents.Transaction;
import com.bank.msbootcointransferyankibatch.models.utils.DataEvent;
import com.bank.msbootcointransferyankibatch.services.ProcessYankiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private ProcessYankiService processYankiService;
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka.topic.yanki}")
    public void consume(String message) {
        log.info("[INI] Consume");
        log.info("Consuming Message {}", message);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            DataEvent<Transaction> dataEvent = objectMapper.readValue(message, new TypeReference<DataEvent<Transaction>>() {});
            processYankiService.process(dataEvent);
        }catch (JsonProcessingException e){
            log.error("JsonProcessingException {}", e.getMessage());
        }

        log.info("[END] Consume");
    }

}