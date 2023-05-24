package com.havelsan.perceivers;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


@Component
public class QueueManager {
    private static QueueManager instance;
    private Queue<String> queue;
    private List<QueueObserver> observers;


    private QueueManager() {
        queue = new LinkedList<>();
        observers = new ArrayList<>();
    }

    public void subscribe(QueueObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(QueueObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (QueueObserver observer : observers) {
            observer.onItemAdded();
        }
    }

    public static synchronized QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
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
