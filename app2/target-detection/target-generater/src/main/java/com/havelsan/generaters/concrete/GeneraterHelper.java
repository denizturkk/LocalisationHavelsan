package com.havelsan.generaters.concrete;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.havelsan.models.GeneraterDataModel;

import java.util.Random;

public class GeneraterHelper {

    private static GeneraterHelper instance;
    private Random random;

    public  GeneraterHelper(){
        random = new Random();
    }

    public static GeneraterHelper getInstance() {
        if (instance == null) {
            synchronized (GeneraterHelper.class) {
                if (instance == null) {
                    instance = new GeneraterHelper();
                }
            }
        }
        return instance;
    }

    public int generateRandomNumber(int x) {
        return random.nextInt(x); // 0-999 arası rastgele bir sayı üretir
    }

    public String objectSerializer(GeneraterDataModel dataModel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dataModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
