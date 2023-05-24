package com.havelsan.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SensorConsumerModel {

    public SensorConsumerModel() {
    }
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

}
