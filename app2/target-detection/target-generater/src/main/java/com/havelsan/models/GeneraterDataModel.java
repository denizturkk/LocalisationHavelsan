package com.havelsan.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneraterDataModel {

    private int id;
    private int x;
    private int y;
    private int targetX;
    private int targetY;

    public GeneraterDataModel(int id, int x, int y, int targetX, int targetY) {
        setId(id);
        setX(x);
        setY(y);
        setTargetX(targetX);
        setTargetY(targetY);
    }
}
