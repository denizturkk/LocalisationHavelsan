package com.example.sensor1;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import static java.lang.Math.*;

@Service
public class Sensor1Service {

    private static final String TOPIC = "sensor1_data";

    Point sensorLocation;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Sensor1Service(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.sensorLocation= new Point();
    }

    @KafkaListener(topics = "target_location", groupId = "sensors-group-1")
    public void consume(String message) {
        String[] coordinates = message.split(",");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        int sensX = sensorLocation.getSensorX();
        int sensY = sensorLocation.getSensorY();

        String result = coordinatesWithAngle(sensX,sensY,x, y);

        System.out.println(result);

        kafkaTemplate.send(TOPIC, result);
    }

    private String coordinatesWithAngle(int sensorX,int sensorY, int x, int y) {
        double dy = sensorY - y;
        double dx = sensorX - x;
        double res = Math.atan2(dy,dx);
        double angle = Math.toDegrees(res);

        String s = sensorX + "," + sensorY + "," + angle;

        return s;
    }

}
