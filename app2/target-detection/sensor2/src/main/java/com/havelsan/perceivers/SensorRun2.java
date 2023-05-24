package com.havelsan.perceivers;

import com.havelsan.models.SensorDataProducerModel2;
import com.havelsan.models.TargetGeneraterConsumeModel2;

import java.time.LocalDateTime;
import java.util.Random;


public class SensorRun2 {
    public SensorDataProducerModel2 generateSensorData(TargetGeneraterConsumeModel2 targetGeneraterConsumeModel) {
        SensorDataProducerModel2 sensorData = new SensorDataProducerModel2();

        sensorData.setId(targetGeneraterConsumeModel.getId());
        sensorData.setX(targetGeneraterConsumeModel.getX());
        sensorData.setY(targetGeneraterConsumeModel.getY());
        sensorData.setTargetX(targetGeneraterConsumeModel.getTargetX());
        sensorData.setTargetY(targetGeneraterConsumeModel.getTargetY());

        int sensorX = getRandomNumber(0, targetGeneraterConsumeModel.getX());
        sensorData.setSensorX(sensorX);

        int sensorY = getRandomNumber(0, targetGeneraterConsumeModel.getY());
        sensorData.setSensorY(sensorY);

        double angle = calculateAngle(targetGeneraterConsumeModel.getTargetX(), targetGeneraterConsumeModel.getTargetY(), sensorX, sensorY);
        sensorData.setAngle(angle);


        return sensorData;
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private double calculateAngle(int targetX, int targetY, int sensorX, int sensorY) {
        double dx = targetX - sensorX;
        double dy = targetY - sensorY;
        return Math.toDegrees(Math.atan2(dy, dx));
    }
}

