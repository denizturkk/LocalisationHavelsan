package com.havelsan.messageBrokers.concretes.kafka;

import com.havelsan.messageBrokers.abstracts.MessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TargetGeneratorKafkaProducer implements MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetGeneratorKafkaProducer.class);
    private KafkaTemplate<String,String> kafkaTemplate;

    public TargetGeneratorKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessages(String message){
        LOGGER.info(String.format("Message sent %s",message));
        kafkaTemplate.send("target_location",message);
    }
}
