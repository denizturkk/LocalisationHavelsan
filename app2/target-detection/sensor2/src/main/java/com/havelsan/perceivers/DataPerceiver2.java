package com.havelsan.perceivers;


import com.havelsan.messageBrokers.abstracts.IMessageProducer2;
import com.havelsan.models.TargetGeneraterConsumeModel2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataPerceiver2 implements QueueObserver2 {

     JsonHelper2 jsonHelper2 = new JsonHelper2();
     SensorRun2 sensorRun2 = new SensorRun2();
      QueueManager2 queueManager2;


     private final IMessageProducer2 messageProducer;

     @Autowired
     public DataPerceiver2(IMessageProducer2 messageProducer) {
          this.messageProducer = messageProducer;
          this.queueManager2 = QueueManager2.getInstance();

     }

     public void sendMessageToBroker(){
          TargetGeneraterConsumeModel2 targetModel = jsonHelper2.getObjectFromJson(queueManager2.removeFromQueue(), TargetGeneraterConsumeModel2.class);
          String message = jsonHelper2.getJsonFromObject(sensorRun2.generateSensorData(targetModel));
          messageProducer.sendMessages(message);
     }

     @Override
     public void onItemAdded() {

          this.sendMessageToBroker();
     }
}












