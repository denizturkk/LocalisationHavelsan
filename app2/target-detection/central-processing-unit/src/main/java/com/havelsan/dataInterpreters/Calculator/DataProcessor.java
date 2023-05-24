package com.havelsan.dataInterpreters.Calculator;

import com.havelsan.dataInterpreters.Queue.CentralQueueA;
import com.havelsan.dataInterpreters.Queue.CentralQueueB;
import com.havelsan.dataInterpreters.Queue.CentralQueueObserverA;
import com.havelsan.dataInterpreters.Queue.CentralQueueObserverB;
import com.havelsan.models.SensorConsumerModel;
import com.havelsan.models.TargetPosition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
@Getter
@Setter
public class DataProcessor implements CentralQueueObserverA, CentralQueueObserverB {

    JsonHelper jsonHelper = new JsonHelper();
    private Queue<String> queueA;
    private Queue<String> queueB;
    private List<String> processedData;
    private List<String> unprocessedQA;
    private List<String> unprocessedQB;

    private boolean controlFlagA;
    private boolean controlFlagB;

    public boolean isControlFlagA() {
        return controlFlagA;
    }

    public boolean isControlFlagB() {
        return controlFlagB;
    }
    private CentralQueueA centralQueueA;
    private CentralQueueB centralQueueB;

    public DataProcessor() {
      setControlFlagA(false);
      setControlFlagB(false);
        //gelen hangi verileri isledik hangilerini senkronizasyon
        //yapamadıgımız icin isleyemedik takib etmek icin
        //4 adet queue
        queueA = new LinkedList<>();
        queueB = new LinkedList<>();
        processedData = new ArrayList<>();
        unprocessedQA = new ArrayList<>();
        unprocessedQB = new ArrayList<>();
        centralQueueA=CentralQueueA.getInstance();
        centralQueueB=CentralQueueB.getInstance();
    }


    private  void monitorData(TargetPosition targetPosition){

        System.out.println("Processed Data:");
        System.out.println("Target X: " + targetPosition.getCalculatedX());
        System.out.println("Target Y: " + targetPosition.getCalculatedY());
        System.out.println("Calculated X: " + targetPosition.getCalculatedX());
        System.out.println("Calculated Y: " + targetPosition.getCalculatedY());
        System.out.println("Sensor 1 X: " + targetPosition.getSensorX1());
        System.out.println("Sensor 1 Y: " + targetPosition.getSensorY1());
        System.out.println("Sensor 2 X: " + targetPosition.getSensorX2());
        System.out.println("Sensor 2 Y: " + targetPosition.getSensorY2());
        System.out.println("/n /n");

    }
    private void synchronizeAndProcessData() {

        while (!unprocessedQA.isEmpty() && !unprocessedQB.isEmpty()) {
            String itemQA = unprocessedQA.get(0);
            String itemQB = unprocessedQB.get(0);


            int idQA = getItemId(itemQA);
            int idQB = getItemId(itemQB);

            if (idQA == idQB) {

                SensorConsumerModel dataQA = jsonHelper.getObjectFromJson(itemQA, SensorConsumerModel.class);
                SensorConsumerModel dataQB = jsonHelper.getObjectFromJson(itemQB, SensorConsumerModel.class);
                TargetPosition result = processData(dataQA, dataQB);
                processedData.add(jsonHelper.getJsonFromObject(result));
                monitorData(result);


                unprocessedQA.remove(0);
                unprocessedQB.remove(0);
            } else {

                break;
            }
        }
    }

    private int getItemId(String item) {
        SensorConsumerModel data = jsonHelper.getObjectFromJson(item, SensorConsumerModel.class);
        return data.getId();
    }


    private void onItemAddedToQueueA(String item) {
        queueA.add(item);
        unprocessedQA.add(item);
        setControlFlagA(true);
        controlCenter();
    }

    private void onItemAddedToQueueB(String item) {
        queueB.add(item);
        unprocessedQB.add(item);
        setControlFlagB(true);
        controlCenter();
    }

    private void controlCenter() {
        if ( isControlFlagA() && isControlFlagB()) {
            synchronizeAndProcessData();
            setControlFlagA(false);
            setControlFlagB(false);
        }
    }

    private TargetPosition processData(SensorConsumerModel sensor1, SensorConsumerModel sensor2) {
        TargetPosition targetPosition = new TargetPosition();


        int sensor1X = sensor1.getSensorX();
        int sensor1Y = sensor1.getSensorY();
        double angle1 = Math.toRadians(sensor1.getAngle());


        int sensor2X = sensor2.getSensorX();
        int sensor2Y = sensor2.getSensorY();
        double angle2 = Math.toRadians(sensor2.getAngle());


        double determinant = Math.sin(angle1) * Math.cos(angle2) - Math.cos(angle1) * Math.sin(angle2);
        if (determinant == 0) {

            targetPosition.setIsParallel(1);
            return targetPosition;
        }

        int targetX1 = (int) ((sensor2Y * Math.sin(angle1) - sensor1Y * Math.sin(angle2)) / determinant);
        int targetY1 = (int) ((sensor1X * Math.cos(angle2) - sensor2X * Math.cos(angle1)) / determinant);

        // hesaplanan hedef
        targetPosition.setCalculatedX(targetX1);
        targetPosition.setCalculatedY(targetY1);

        //sensor1 den gelen veri: sensor1'in aci hesapladigi hedefin gercekte bulundugu konum(test amacli)
        targetPosition.setTargetX1(sensor1.getTargetX());
        targetPosition.setTargetY1(sensor1.getTargetY());

        //sensor2 den gelen veri: sensor2'nin aci hesapladigi hedefin gercekte bulundugu konum(test amacli)
        targetPosition.setTargetY2(sensor2.getTargetY());
        targetPosition.setTargetX2(sensor2.getTargetX());


        targetPosition.setSensorX1(sensor1X);
        targetPosition.setSensorY1(sensor1Y);
        targetPosition.setSensorX2(sensor2X);
        targetPosition.setSensorY2(sensor2Y);


        return targetPosition;
    }



    public List<String> getUnprocessedDataQA() {
        return unprocessedQA;
    }

    public List<String> getUnprocessedDataQB() {
        return unprocessedQB;
    }


    @Override
    public void onItemAddedtoA() {
        this.onItemAddedToQueueA(centralQueueA.removeFromQueue());

    }

    @Override
    public void onItemAddedtoB() {
        this.onItemAddedToQueueB(centralQueueB.removeFromQueue());
    }
}