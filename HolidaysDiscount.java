package com.hawm.hawm.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HolidaysDiscount extends Decorator {
    //RidesHelper ride;
    static Set<LocalDate> holidays=new HashSet<>();

    public static void InsertAllHoliday(){
        holidays.add(LocalDate.now());

    }
    HolidaysDiscount(RidesHelper ride){
        this.ride=ride;InsertAllHoliday();
    }


    @Override
    public double cost() {
        return ride.cost()-(0.05*ride.cost());}
}
