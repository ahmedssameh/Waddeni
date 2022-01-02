package com.hawm.hawm.model;

public abstract class Decorator implements RidesHelper {
    RidesHelper ride;
    public abstract double cost();
}
