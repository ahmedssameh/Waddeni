package com.hawm.hawm.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Repository("driver")
public class Driver extends Person /*implements Subject/*,Subject*/{
    private int license;
    private int nationalId;
    private float averagerate=0;
    private Boolean status=true;
    private double Balance=0;
    private Map<User,Double> usersrate=new HashMap();
    private ArrayList<String> favorite_area=new ArrayList<>();
    ArrayList<Observer> observersUser =new ArrayList<>();
    ArrayList<Observer> observerAdmin=new ArrayList<>();


    public void setBalance(double balance) {
        Balance += balance;
    }

    public double getBalance() {
        return Balance;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Driver(){}

    public Driver(@JsonProperty String mobile,@JsonProperty String userName, @JsonProperty String email,
                  @JsonProperty String password, @JsonProperty int license,@JsonProperty int nationalId) {
        super(mobile, userName, email, password, Status.valueOf("Suspend"));
        this.license=license;
        this.nationalId=nationalId;
    }
    public boolean  register(@Qualifier("driver") Person d){
        if(memory.getPersons().contains(d)){
            System.out.println("Driver is already registered");
            return false;
        }else{
            Admin.getPendingDrivers().add((Driver) d);
            System.out.println("wait for the admin choice");
            return true;
        }
    }
    public int getLicense() {
        return license;
    }
    public Map<User, Double> getUsersrate() {
        return usersrate;
    }
    public int getNationalId() {
        return nationalId;
    }
    public float getAveragerate() {
        AverageDriverRate r=new AverageDriverRate();
        r.calculateAvgRate(this);
        return averagerate;
    }

    public ArrayList<String> getFavorite_area() {
        return favorite_area;
    }
    public void addFavoritesArea(String predestination) {
        favorite_area.add(predestination);
    }
    public ArrayList<Rides> showRidesForSpecificArea(String predestination) {
        ArrayList<Rides>  DriverRides=new ArrayList<>();
        for(Rides r:memory.getRides()){
            if(r.Details.getDestination().equalsIgnoreCase(predestination)){
                DriverRides.add(r);
            }
        }
        return DriverRides;
    }
    public void setAveragerate(float averagerate) {
        this.averagerate = averagerate;
    }
    public void setMoney(Rides r,double moneypay){

        r.setOffers(this,moneypay);
        //notifyAllObserver();
    }
}












 /* @Override
    public void update() {
        for(Rides R:memory.getRides()){
            if(favorite_area.contains(R.getDestination())){
                System.out.println("Now has new ride from "+R.getSource()+" to "+R.getDestination());
            }
        }
    }
    //when Set offer driver now subject
    @Override
    public void subscribers(Observer observer) {
        observersUser.add(observer);
        observerAdmin.add(observer);
    }

    @Override
    public void unSubscribers(Observer observer) {
        observersUser.remove(observer);
        observerAdmin.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer O : observersUser) {
            O.update();
        }
        for (Observer O : observerAdmin) {
            O.update();
        }
    }*/
