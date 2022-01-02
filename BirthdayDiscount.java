package com.hawm.hawm.model;

public class BirthdayDiscount extends Decorator {
    //RidesHelper ride;
    BirthdayDiscount(RidesHelper ride){
        this.ride=ride;
    }


    @Override
    public double cost() {
        return ride.cost()-(0.1*ride.cost());

    }
}
