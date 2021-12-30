package com.company;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class Rides implements Subject{
    ArrayList<Observer> observersAdmin=new ArrayList<>();
    private static int id=0;
    private int idRide;
    private String source;
    private String destination;
    private double offer=-1;
    private boolean ended=false;
    private int numberOfPassengers;
    private boolean availableRide =true;
    private ArrayList<User> user=new ArrayList<>();
    private Driver driver=null;
    private LocalDateTime now = LocalDateTime.now();
    private boolean ISArrivedSource=false;
    private boolean ISArrivedDistination=false;
    private Map<Driver,Double> offers=new HashMap<>();


    public ArrayList<User> getUser() {
        return user;
    }
    public void setUser(User user) {
        this.getUser().add(user);
    }
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
    public boolean availableRide() {
        return availableRide;
    }
    public void setavailableRide(boolean availableRide) {
        availableRide= availableRide;
    }
    Rides(){
        this.idRide= id++;
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
    public LocalDateTime getNow() {
        return now;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
        this.driver.setStatus(false);
    }
    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setOffers(Driver d,double n) {
        offers.put(d,n);
        notifyAllObserver();
    }
    public void setOffer(double offer) {
        this.offer = offer;
        notifyAllObserver();
    }
    public static int getId() {
        return id;
    }
    public void setEnded(boolean ended) {
        this.ended = ended;
    }
    public Driver getDriver() {
        return driver;
    }
    public Map<Driver, Double> getOffers() {
        return offers;
    }
    public String getDestination() {
        return destination;
    }
    public String getSource() {
        return source;
    }
    public boolean isEnded() {
        return ended;
    }
    public double getOffer() {
        return offer;
    }
    public int getIdRide() {
        return idRide;
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

