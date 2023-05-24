package com.havelsan.messageBrokers.concretes;




import com.havelsan.messageBrokers.abstracts.IMessageConsumer;
import com.havelsan.perceivers.QueueManager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;




@Service
@Getter
@Setter
public class TargetConsumer implements IMessageConsumer {


    private static final Logger LOGGER = LoggerFactory.getLogger(TargetConsumer.class);

    QueueManager queueManager = QueueManager.getInstance();

    @KafkaListener(topics = "target_location", groupId ="sensor-group-1")
    public void consume(String message) {
        LOGGER.info(String.format("Data received -> %s", message));
        queueManager.addToQueue(message);

    }

}