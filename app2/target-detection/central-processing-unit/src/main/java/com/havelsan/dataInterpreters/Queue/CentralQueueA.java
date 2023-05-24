package com.havelsan.dataInterpreters.Queue;


import org.springframework.stereotype.Component;

import java.util.*;

import java.util.LinkedList;
import java.util.Queue;


@Component
public class CentralQueueA {
    private static CentralQueueA instance;
    private Queue<String> queue;
    private List<CentralQueueObserverA> observers;


    private CentralQueueA() {
        queue = new LinkedList<>();
        observers = new ArrayList<>();
    }

    public void subscribe(CentralQueueObserverA observer) {
        observers.add(observer);
    }

    public void unsubscribe(CentralQueueObserverA observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CentralQueueObserverA observer : observers) {
            observer.onItemAddedtoA();
        }
    }

    public static synchronized CentralQueueA getInstance() {
        if (instance == null) {
            instance = new CentralQueueA();
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
