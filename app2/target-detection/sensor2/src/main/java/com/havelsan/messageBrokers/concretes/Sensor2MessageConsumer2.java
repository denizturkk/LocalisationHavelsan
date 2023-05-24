package com.havelsan.messageBrokers.concretes;

import com.havelsan.messageBrokers.abstracts.IMessageConsumer2;
import com.havelsan.perceivers.QueueManager2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Sensor2MessageConsumer2 implements IMessageConsumer2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sensor2MessageConsumer2.class);

    QueueManager2 queueManager = QueueManager2.getInstance();

    @KafkaListener(topics="target_location",groupId="sensor-group-2")
    public void consume(String message){

     LOGGER.info(String.format("data received -> %s",message));
      queueManager.addToQueue(message);

    }

}
