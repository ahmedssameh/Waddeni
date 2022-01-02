package com.hawm.hawm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class RideDetails {
    private Driver driver=null;
    private ArrayList<User> user=new ArrayList<>();
    private String source;
    private String destination;
    private LocalDate date= LocalDate.now();
    private LocalTime time = LocalTime.now();
    private boolean availableRide =true;
    private boolean ended=false;
    public void setDriver(Driver driver) {
        this.driver = driver;
        this.driver.setStatus(false);
    }
    public Driver getDriver() {
        return driver;
    }
    public void setUser(User user) {
        this.getUser().add(user);
    }
    public ArrayList<User> getUser() {
        return user;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDestination() {
        return destination;
    }
    public String getSource() {
        return source;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public boolean availableRide() {
        return availableRide;
    }
    public void setavailableRide(boolean availableRide) {
        availableRide= availableRide;
    }
    public boolean isEnded() {
        return ended;
    }
    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
