package com.havelsan.perceivers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class QueueManager2 {
    private static QueueManager2 instance;
    private Queue<String> queue;
    private List<QueueObserver2> observers;


    QueueManager2() {
        queue = new LinkedList<>();
        observers = new ArrayList<>();
    }

    public void subscribe(QueueObserver2 observer) {observers.add(observer);}

    public void unsubscribe(QueueObserver2 observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (QueueObserver2 observer : observers) {
            observer.onItemAdded();
        }
    }

    public static synchronized QueueManager2 getInstance() {
        if (instance == null) {
            instance = new QueueManager2();
        }
        return instance;
    }

    public void addToQueue(String item) {
        queue.add(item);
        notifyObservers();
    }

    public String removeFromQueue() {
        return queue.poll();
    }

    public int getQueueSize() {
        return queue.size();
    }
}