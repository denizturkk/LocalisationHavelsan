package com.havelsan.perceivers;

import java.util.Random;

    public class PerceiverHelper2 {

    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound ) + lowerBound;
    }

}
