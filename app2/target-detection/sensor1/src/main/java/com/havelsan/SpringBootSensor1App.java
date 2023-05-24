package com.havelsan;


import com.havelsan.perceivers.DataPerceiver;
import com.havelsan.perceivers.QueueManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(exclude = SpringApplicationAdminJmxAutoConfiguration.class)

public class SpringBootSensor1App {


    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringBootSensor1App.class);
        QueueManager queueManager = QueueManager.getInstance();
        DataPerceiver dataPerceiver = context.getBean(DataPerceiver.class);
        queueManager.subscribe(dataPerceiver);

    }
}