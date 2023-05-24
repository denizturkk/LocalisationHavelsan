package com.havelsan.perceivers;

import com.google.gson.Gson;

import java.util.Random;

    public class PerceiverHelper {

    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound ) + lowerBound;
    }

}
