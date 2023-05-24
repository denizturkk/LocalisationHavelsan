package com.havelsan.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorDataProducerModel2 {

    private int sensorId;
    private int id;
    private int x;
    private int y;
    private int targetX;
    private int targetY;
    private boolean isProcessed;

    private int sensorX;
    private int sensorY;
    private double angle;

    public SensorDataProducerModel2() {
        this.isProcessed = false;
        this.sensorId=2;
    }
}
