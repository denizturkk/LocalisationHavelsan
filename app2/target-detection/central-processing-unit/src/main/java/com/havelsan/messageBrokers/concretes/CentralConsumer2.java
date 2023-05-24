package com.havelsan.messageBrokers.concretes;

import com.havelsan.dataInterpreters.Queue.CentralQueueA;
import com.havelsan.dataInterpreters.Queue.CentralQueueB;
import com.havelsan.messageBrokers.abstracts.ICentralMessageConsumer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
    public class CentralConsumer2 implements ICentralMessageConsumer
    {


    private static final Logger LOGGER = LoggerFactory.getLogger(CentralConsumer2.class);

    CentralQueueB queueManager = CentralQueueB.getInstance();

    @KafkaListener(topics = "sensor2_data", groupId = "center-group")
    public void consume(String message) {
        LOGGER.info(String.format("RECEIVED BY 2 -> %s", message));
        queueManager.addToQueue(message);
    }


}