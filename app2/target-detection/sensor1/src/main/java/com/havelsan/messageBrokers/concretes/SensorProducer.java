package com.havelsan.messageBrokers.concretes;


import com.havelsan.messageBrokers.abstracts.IMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SensorProducer implements IMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorProducer.class);
    private final KafkaTemplate<String,String> kafkaTemplate;

    public SensorProducer(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessages(String message){
        LOGGER.info(String.format("Message sent %s",message));
        kafkaTemplate.send("sensor1_data",message);
    }
}
