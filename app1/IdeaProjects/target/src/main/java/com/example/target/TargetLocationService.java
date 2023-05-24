package com.example.target;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TargetLocationService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    public TargetLocationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void sendTargetLocation() {
        int x = random.nextInt(1000);
        int y = random.nextInt(1000);

        String message = x + "," + y;

        System.out.println("msg: "+message);
        kafkaTemplate.send("target_location", message);
    }

}
