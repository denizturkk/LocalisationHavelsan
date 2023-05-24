package com.example.sensor2;

import java.util.Random;

public class Point {
    Random random = new Random();
    private int sensorX;
    private int sensorY;

    public Point() {
        this.sensorX = random.nextInt(1000);
        this.sensorY = random.nextInt(1000);
    }

    public int getSensorX() {
        return sensorX;
    }

    public int getSensorY() {
        return sensorY;
    }

}
