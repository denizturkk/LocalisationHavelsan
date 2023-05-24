package com.havelsan;


import com.havelsan.perceivers.DataPerceiver2;
import com.havelsan.perceivers.QueueManager2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = SpringApplicationAdminJmxAutoConfiguration.class)
public class SpringBootSensor2Application
{
    public static void main( String[] args )
    {
        ApplicationContext context = SpringApplication.run(SpringBootSensor2Application.class);
        SpringApplication.run(SpringBootSensor2Application.class);
        QueueManager2 queueManager = QueueManager2.getInstance();
        DataPerceiver2 dataPerceiver2 = context.getBean(DataPerceiver2.class);
        queueManager.subscribe(dataPerceiver2);


    }
}
