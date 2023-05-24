package com.havelsan.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetConsumeModel implements IConsumeModel {

    private int id;
    private int x;
    private int y;
    private int targetX;
    private int targetY;

    public TargetConsumeModel() {
    }

    public TargetConsumeModel(int id, int x, int y, int targetX, int targetY) {
        setId(id);
        setX(x);
        setY(y);
        setTargetX(targetX);
        setTargetY(targetY);
    }
}
