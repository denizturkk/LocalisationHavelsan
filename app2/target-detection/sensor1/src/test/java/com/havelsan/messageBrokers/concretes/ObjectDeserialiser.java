package com.havelsan.messageBrokers.concretes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectDeserialiser {
    private ObjectMapper objectMapper;

    public ObjectDeserialiser() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T deserialize(String json, Class<T> targetType) {
        try {
            return objectMapper.readValue(json, targetType);
        } catch (Exception e) {
            // JSON dönüşüm hatası durumunda ilgili işlemler yapılabilir
            e.printStackTrace();
            return null;
        }
    }
}
