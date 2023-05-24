package com.havelsan.generaters.concrete;

import com.havelsan.models.GeneraterDataModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class DataGenerater {

    private int tempId;
    private int tempX;
    private int tempY;
    GeneraterHelper generatorHelperInstance=GeneraterHelper.getInstance();

    private static int counter=0;

    public DataGenerater() {

        setTempX(GeneraterParameters.worldConstantX);
        setTempY(GeneraterParameters.worldConstantY);
    }

    public String generateData() {
        String message;
        setTempId(counter);
        GeneraterDataModel dataModel=new GeneraterDataModel(getTempId(),getTempX(),getTempY(),generateRandom(getTempX()),generateRandom(getTempY()));
        message=generatorHelperInstance.objectSerializer(dataModel);
        counter++;
        return message;

    }

    public int generateRandom(int x){
        return generatorHelperInstance.generateRandomNumber(x);
    }


}
