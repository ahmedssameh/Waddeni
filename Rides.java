package com.hawm.hawm.model;

import java.time.LocalDate;
import java.util.*;
public  class Rides implements Subject, RidesHelper {
    ArrayList<Observer> observersAdmin=new ArrayList<>();
    private static int id=0;
    private int idRide;
    private double offer=-1;
    private Map<Driver,Double> offers=new HashMap<>();
    private int numberOfPassengers;;
    private boolean ISArrivedSource=false;
    private boolean ISArrivedDistination=false;
    public RideDetails Details=new RideDetails();
    Rides(){
        this.idRide= id++;
    }
    public void Discounts(RidesHelper r){
        if(this.Details.getUser().size()>=2){
            System.out.println(1);
            r=new TwoPassengersDiscount(r);
            //offer=r.cost();
        }
        for(LocalDate LD:HolidaysDiscount.holidays) {
            if (this.Details.getDate().equals(LD)) {
                System.out.println(2);
                r = new HolidaysDiscount(r);
            }
        }
        for(User u:this.Details.getUser()) {
            if (u.IsFirstRide){
                System.out.println(3);
                u.IsFirstRide=false;
                r=new FirstRideDiscount(r);
                //offer=r.cost();
                break;}
        }
        for(String S: Admin.getDiscountPlaces()) {

            if (this.Details.getDestination().equalsIgnoreCase(S)){
                System.out.println(4);
                r=new AdminDiscount(r);
                //offer=r.cost();
                break;
            }
        }
        for(User u:this.Details.getUser()) {
            if (u.birthday.equals(LocalDate.now())){
                System.out.println(5);
                r=new BirthdayDiscount(r);

                break;}
        }
        offer=r.cost();
    }
    public double cost(){return offer;}
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public boolean isISArrivedSource() {
        return ISArrivedSource;
    }
    public boolean isISArrivedDistination() {
        return ISArrivedDistination;
    }
    public void setISArrivedSource(boolean ISArrivedSource) {
        this.ISArrivedSource = ISArrivedSource;
        notifyAllObserver();
    }
    public void setISArrivedDistination(boolean ISArrivedDistination) {
        this.ISArrivedDistination = ISArrivedDistination;
        notifyAllObserver();
    }
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
    public double getOffer() {
        return offer;
    }
    public int getIdRide() {
        return idRide;
    }
    public void setOffers(Driver d,double n) {
        offers.put(d,n);
        notifyAllObserver();
    }
    public void setOffer(double offer) {
        this.offer = offer;
        this.Details.getDriver().setBalance(offer);
        notifyAllObserver();
        Discounts(this);

    }
    public Map<Driver, Double> getOffers() {
        return offers;
    }
    @Override
    public void subscribers(Observer observer) {
        observersAdmin.add(observer);
    }
    @Override
    public void unSubscribers(Observer observer) {
        observersAdmin.remove(observer);
    }
    @Override
    public void notifyAllObserver() {

        for(Observer o:observersAdmin){

            o.update(this);
        }
    }
}


