package com.havelsan.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorProducerModel {

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

    public SensorProducerModel() {
        this.isProcessed = false;
        this.sensorId=1;
    }
}
