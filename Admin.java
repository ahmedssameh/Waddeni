package com.company;

import java.util.*;

public class Admin extends Observer {
    private static ArrayList<Driver> pendingDrivers=new ArrayList<>();
    private static Set<String> Events=new HashSet<>();

    public static Set<String> getEvents() {
        return Events;
    }

    private static Admin admin;
    HandleData memory=Memory.getInstance();


    /*private Admin(Rides subject) {
        this.subject=subject;
        this.subject.subscribers(this);
    }*/
    private Admin(){}

    public static ArrayList<Driver> getPendingDrivers() {
        return pendingDrivers;
    }

    public static Admin getInstance(){
        if(admin==null){
            admin=new Admin();
        }
        return admin;
    }
    /*public static Admin getInstance2(Rides subject){
        admin=new Admin(subject);
        return admin;
    }*/
    public void verifyDriver(Person p){
        p.setStat(Status.Active);
        memory.getPersons().add(p);
        pendingDrivers.remove(p);
        System.out.println(p.getUserName()+" is verified");
    }
    public void suspend(Person p){
        p.setStat(Status.Suspend);
        System.out.println(p.getUserName()+" is suspend");
    }
    @Override
    public void update(Rides R) {
        String event;
        //Admin.getInstance2(R);

        if(R.isISArrivedDistination()){
            for(int i=0;i<R.getUser().size();i++) {
                event = "Captain Arrived this ride to source " + " Ride name " + R.getIdRide() + "current time " + R.getNow()
                        + "Captain " + R.getDriver().getUserName() + "User " + R.getUser().get(i).getUserName();

                Events.add(event);
            }
        }
        if(R.isISArrivedSource()){
            for(int i=0;i<R.getUser().size();i++) {
                event = "Captain Arrived this ride to source " + " Ride name " + R.getIdRide() + "current time " + R.getNow()
                        + "Captain " + R.getDriver().getUserName() + "User " + R.getUser().get(i).getUserName();
                System.out.println(3);
                Events.add(event);
            }
        }
        if(!R.getOffers().isEmpty()){
            for(Map.Entry<Driver, Double> T : R.getOffers().entrySet()){
                if(T.getKey().equals(R.getDriver())){
                    event="Captain Sets offer "+" Ride name "+R.getIdRide()+" current time "+R.getNow()
                            +" Captain "+R.getDriver().getUserName()+" with offer "+R.getOffer();
                    Events.add(event);
                }

            }
        }
        if(R.getOffer()!=-1){
            for(int i=0;i<R.getUser().size();i++) {
                event = "User accept ride " + " Ride name " + R.getIdRide() + " current time " + R.getNow()
                        + " User " + R.getUser().get(i).getUserName();
                Events.add(event);

            }
        }
    }
}

