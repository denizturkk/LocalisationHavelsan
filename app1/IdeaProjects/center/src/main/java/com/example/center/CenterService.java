package com.example.center;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class CenterService {

    private Queue<Line> queueOne = new LinkedBlockingQueue<>();
    private Queue<Line> queueTwo = new LinkedBlockingQueue<>();
    Point sensor1,sensor2,intersect;
    Line line1, line2;

    int sensor1x,sensor1y,sensor2x,sensor2y;
    double angle1, angle2;

    @KafkaListener(topics = "sensor1_data", groupId = "center-group")
    public void consumeSensor1Data(String message) {
        String[] data = message.split(",");
        sensor1x = Integer.parseInt(data[0]);
        sensor1y= Integer.parseInt(data[1]);
        angle1 = Math.toRadians(Double.parseDouble(data[2]));

        sensor1 = new Point(sensor1x,sensor1y);

        line1 = new Line(sensor1, angle1);

        queueOne.add(line1);

        computeSum();
    }

    @KafkaListener(topics = "sensor2_data", groupId = "center-group")
    public void consumeSensor2Data(String message) {
        String[] data = message.split(",");
        sensor2x = Integer.parseInt(data[0]);
        sensor2y= Integer.parseInt(data[1]);
        angle2 = Math.toRadians(Double.parseDouble(data[2]));

        sensor2 = new Point(sensor2x,sensor2y);

        line2 = new Line(sensor2,angle2);

        queueTwo.add(line2);

        computeSum();

    }

    private synchronized void computeSum() {
        if (!queueOne.isEmpty() && !queueTwo.isEmpty()) {
            Line l1 = queueOne.poll();
            Line l2 = queueTwo.poll();
            intersect = Line.intersect(l1,l2);
            if (intersect != null) {
                String s = "Computed target coordinates :"+intersect.x + "," +intersect.y;
                System.out.println(s);
            } else {
                System.out.println("The lines from sensor1 and sensor2 are parallel and do not intersect. Restart the sensor apps.");
            }
        }
    }
}
