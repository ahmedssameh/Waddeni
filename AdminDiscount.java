package com.hawm.hawm.model;

public class AdminDiscount extends Decorator {
    //RidesHelper ride;
    AdminDiscount(RidesHelper ride){
        this.ride=ride;
    }


    @Override
    public double cost() {
        return ride.cost()-(0.1*ride.cost());

    }
}

