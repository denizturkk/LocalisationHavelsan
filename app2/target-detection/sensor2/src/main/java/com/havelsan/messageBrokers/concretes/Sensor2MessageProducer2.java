package com.havelsan.messageBrokers.concretes;

import com.havelsan.messageBrokers.abstracts.IMessageProducer2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sensor2MessageProducer2 implements IMessageProducer2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sensor2MessageProducer2.class);
    private KafkaTemplate<String,String> kafkaTemplate;

    public Sensor2MessageProducer2(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessages(String message){
        LOGGER.info(String.format("Message sent %s",message));
        kafkaTemplate.send("sensor2_data",message);
    }
}
