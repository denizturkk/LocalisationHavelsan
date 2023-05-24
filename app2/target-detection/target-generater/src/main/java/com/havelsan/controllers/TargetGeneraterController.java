package com.havelsan.controllers;

import com.havelsan.generaters.concrete.DataGenerater;
import com.havelsan.messageBrokers.abstracts.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/targetgenerater")
public class TargetGeneraterController {

    private MessageProducer messageProducer;
    private final DataGenerater dataGenerater;

    public TargetGeneraterController(MessageProducer messageProducer, DataGenerater dataGenerator) {
        this.messageProducer = messageProducer;
        this.dataGenerater=dataGenerator;
    }

    //http:localhost:8080/api/v1/targetgenerater/startgenerater
    @GetMapping("/startgenerater")
    public ResponseEntity<String> startgenerater(){
        String message =dataGenerater.generateData();
        messageProducer.sendMessages(message);
        return ResponseEntity.ok(message);
    }


}

