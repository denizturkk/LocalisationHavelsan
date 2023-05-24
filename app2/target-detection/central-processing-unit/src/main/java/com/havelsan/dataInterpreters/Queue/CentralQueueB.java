package com.havelsan.dataInterpreters.Queue;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class CentralQueueB  {
    private static CentralQueueB instance;
    private Queue<String> queue;
    private List<CentralQueueObserverB> observers;


    private CentralQueueB() {
        queue = new LinkedList<>();
        observers = new ArrayList<>();
    }

    public void subscribe(CentralQueueObserverB observer) {
        observers.add(observer);
    }

    public void unsubscribe(CentralQueueObserverB observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CentralQueueObserverB observer : observers) {
            observer.onItemAddedtoB();
        }
    }

    public static synchronized CentralQueueB getInstance() {
        if (instance == null) {
            instance = new CentralQueueB();
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
