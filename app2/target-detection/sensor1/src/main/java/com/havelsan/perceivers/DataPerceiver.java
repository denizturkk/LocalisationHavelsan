package com.havelsan.perceivers;


import com.havelsan.messageBrokers.abstracts.IMessageProducer;
import com.havelsan.models.TargetConsumeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataPerceiver implements QueueObserver{


     JsonHelper jsonHelper = new JsonHelper();
     SensorRun sensorRun = new SensorRun();
     QueueManager queueManager;

     @Autowired
     private IMessageProducer messageProducer;

     public DataPerceiver(IMessageProducer messageProducer) {
          this.messageProducer = messageProducer;
          queueManager=QueueManager.getInstance();


     }


     public void sendMessageToBroker(){
          TargetConsumeModel targetModel = jsonHelper.getObjectFromJson(queueManager.removeFromQueue(), TargetConsumeModel.class);
          String message = jsonHelper.getJsonFromObject(sensorRun.generateSensorData(targetModel));
          messageProducer.sendMessages(message);

     }


     @Override
     public void onItemAdded() {
          this.sendMessageToBroker();
     }
}





