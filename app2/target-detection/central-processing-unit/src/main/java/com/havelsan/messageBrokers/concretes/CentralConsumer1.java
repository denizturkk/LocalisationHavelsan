package com.havelsan.messageBrokers.concretes;

import com.havelsan.dataInterpreters.Queue.CentralQueueA;
import com.havelsan.messageBrokers.abstracts.ICentralMessageConsumer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

public class CentralConsumer1 {

    @Service
    @Getter
    @Setter
    public class CentralCustomer1 implements ICentralMessageConsumer {


        private static final Logger LOGGER = LoggerFactory.getLogger(CentralCustomer1.class);

        CentralQueueA queueManager = CentralQueueA.getInstance();

        @KafkaListener(topics = "sensor1_data", groupId = "center-group")
        public void consume(String message) {
            LOGGER.info(String.format("RECEIVED BY 1 %s", message));
            queueManager.addToQueue(message);

        }

    }
}
