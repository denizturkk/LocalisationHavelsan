package com.havelsan.perceivers;

import com.havelsan.models.SensorProducerModel;
import com.havelsan.models.TargetConsumeModel;

import java.util.Random;


public class SensorRun {


    public SensorProducerModel generateSensorData(TargetConsumeModel targetConsumeModel) {
        SensorProducerModel sensorData = new SensorProducerModel();

        sensorData.setId(targetConsumeModel.getId());
        sensorData.setX(targetConsumeModel.getX());
        sensorData.setY(targetConsumeModel.getY());
        sensorData.setTargetX(targetConsumeModel.getTargetX());
        sensorData.setTargetY(targetConsumeModel.getTargetY());

        int sensorX = getRandomNumber(0, targetConsumeModel.getX());
        sensorData.setSensorX(sensorX);

        int sensorY = getRandomNumber(0, targetConsumeModel.getY());
        sensorData.setSensorY(sensorY);

        double angle = calculateAngle(targetConsumeModel.getTargetX(), targetConsumeModel.getTargetY(), sensorX, sensorY);
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

