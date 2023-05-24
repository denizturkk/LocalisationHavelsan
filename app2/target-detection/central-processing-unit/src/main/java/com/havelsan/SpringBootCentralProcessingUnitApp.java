package com.havelsan;

import com.havelsan.dataInterpreters.Calculator.DataProcessor;
import com.havelsan.dataInterpreters.Queue.CentralQueueA;
import com.havelsan.dataInterpreters.Queue.CentralQueueB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(exclude = SpringApplicationAdminJmxAutoConfiguration.class)

public class SpringBootCentralProcessingUnitApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootCentralProcessingUnitApp.class);

        ApplicationContext context = SpringApplication.run(SpringBootCentralProcessingUnitApp.class);
        DataProcessor dataProcessor = context.getBean(DataProcessor.class);
        CentralQueueA centralQueueA= CentralQueueA.getInstance();
        CentralQueueB centralQueueB =CentralQueueB.getInstance();

        //dataProccesor sınıfı 2 farklı mesaj kuyruguna observer
        //pattern ile abone olur mesaj ,kuyruklar veriyi depolar ve kafkadan
        // veri queue mekanizması geldikçe veri işleme merkezindeki control birimini tetikler
    
        centralQueueB.subscribe(dataProcessor);
        centralQueueA.subscribe(dataProcessor);

    }
}
