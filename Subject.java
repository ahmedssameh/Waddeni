package com.hawm.hawm.model;


import com.hawm.hawm.model.Observer;

public interface Subject {
    void subscribers(Observer observer);
    void unSubscribers(Observer observer);
    void notifyAllObserver();
}

