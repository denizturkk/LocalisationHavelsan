package com.havelsan.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetPosition {
    private int isParallel;

    // target(X,Y)1  VE target(X,Y)2
    // sistemin basarısını ölçmek için kullanilacak
    //hesaplamalarda kullanılmaz
    //birbiri ile karsilastirip veri senkronizasyonun basarili
    // olup olmadigini anlayabiliriz

    //from sensor1
    private int targetX1;
    private int targetY1;

    //from sensor2
    private int targetX2;
    private int targetY2;


    private int sensorX1;
    private int sensorY1;
    private int sensorX2;
    private int sensorY2;
    private int calculatedX;
    private int calculatedY;

}
