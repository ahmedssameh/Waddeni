package com.hawm.hawm.model;

public class FirstRideDiscount extends Decorator {
    //RidesHelper ride;
    FirstRideDiscount(RidesHelper ride){
        this.ride=ride;
    }

    @Override
    public double cost() {
        return ride.cost()-(0.1*ride.cost());
    }
}

