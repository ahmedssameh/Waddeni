package com.hawm.hawm.model;



import java.util.*;

public class Admin extends Observer {
    private static ArrayList<Driver> pendingDrivers=new ArrayList<>();
    private static Set<String> Events=new HashSet<>();
    private static ArrayList<String> DiscountPlaces=new ArrayList<>();
    private final String admin_user_name="HAWM";
    private final String admin_password="HAWM2021-2022";
    public static Set<String> getEvents() {
        return Events;
    }

    private static Admin admin;
    HandleData memory= Memory.getInstance();

    public String getAdmin_user_name() {
        return admin_user_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }
    private Admin(){}
    public static ArrayList<Driver> getPendingDrivers() {
        return pendingDrivers;
    }
    public static ArrayList<String> getDiscountPlaces() {
        return DiscountPlaces;
    }
    public static Admin getInstance(){
        if(admin==null){
            admin=new Admin();
        }
        return admin;
    }
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
    public void addDiscountPlaces(String place){
        DiscountPlaces.add(place);
    }
    @Override
    public void update(Rides R) {
        String event;
        //Admin.getInstance2(R);

        if(R.isISArrivedDistination()){
            for(int i=0;i<R.Details.getUser().size();i++) {
                event = "Captain Arrived this ride to source " + " Ride name " + R.getIdRide() + "current time " + R.Details.getDate()
                        +' '+R.Details.getTime()
                        + "Captain " + R.Details.getDriver().getUserName() + "User " + R.Details.getUser().get(i).getUserName();

                Events.add(event);
            }
        }
        if(R.isISArrivedSource()){
            for(int i=0;i<R.Details.getUser().size();i++) {
                event = "Captain Arrived this ride to source " + " Ride name " + R.getIdRide() + "current time " + R.Details.getDate()
                        +' '+R.Details.getTime()
                        + "Captain " + R.Details.getDriver().getUserName() + "User " + R.Details.getUser().get(i).getUserName();
                System.out.println(3);
                Events.add(event);
            }
        }
        if(!R.getOffers().isEmpty()){
            for(Map.Entry<Driver, Double> T : R.getOffers().entrySet()){
                if(T.getKey().equals(R.Details.getDriver())){
                    event="Captain Sets offer "+" Ride name "+R.getIdRide()+" current time "+R.Details.getDate()
                            +' '+R.Details.getTime()
                            +" Captain "+R.Details.getDriver().getUserName()+" with offer "+R.getOffer();
                    Events.add(event);
                }

            }
        }
        if(R.getOffer()!=-1){
            for(int i=0;i<R.Details.getUser().size();i++) {
                event = "User accept ride " + " Ride name " + R.getIdRide() + " current time " + R.Details.getDate()+' '+R.Details.getTime()
                        + " User " + R.Details.getUser().get(i).getUserName();
                Events.add(event);

            }
        }
    }
}

