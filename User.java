package com.company;
import java.util.ArrayList;

public class User extends Person /*implements Subject,Observer*/{
    ArrayList<Observer> observersDrivers =new ArrayList<>();
    public User(){}
    public User(String mobile, String userName, String email, String password) {
        super(mobile, userName, email, password,Status.valueOf("Active"));
    }
    public boolean register(Person p){
        if(memory.getPersons().contains(p)){
            System.out.println("User is already registered");
            return false;
        }else {
            memory.getPersons().add(p);
            return true;
        }
    }
    public Rides createRide(String source,String destination,int numPass){
        int f=0;

        if(numPass>1) {
            for (Rides r : memory.getRides()) {
                if (r.getDestination().equalsIgnoreCase(destination) &&
                        r.getSource().equalsIgnoreCase(source) &&
                        r.getNumberOfPassengers() == numPass&&r.availableRide()) {
                    r.setUser(this);
                    if(r.getNumberOfPassengers()==r.getUser().size()) r.setavailableRide(false);
                    return r;
                }


            }

        }
        if(numPass>=1) {
            Rides r = new Rides();
            r.subscribers(Admin.getInstance());
            r.setSource(source);
            r.setDestination(destination);
            r.setUser(this);
            r.setNumberOfPassengers(numPass);
            if(numPass==1) {r.setavailableRide(false);}
            memory.getRides().add(r);
            return r;
        }
        //notifyAllObserver();
        return null;
    }
    public void setRate(Driver D,double rate){
        D.getUsersrate().put(this,rate);
    }
    public float ShowavrageRate(Driver D){
        return D.getAveragerate();
    }

}

    /*@Override
    public void subscribers(Observer observer) {
        observersDrivers.add(observer);
    }

    @Override
    public void unSubscribers(Observer observer) {
        observersDrivers.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for(Observer O: observersDrivers){
            O.update();
        }
    }



    @Override
    public void update() {
        for(Rides r:memory.getRides()){
            if(r.getUser().equals(this)){
                //r.getOffers().add(" offer for ride"+r.getOffer());
                System.out.println(" offer for ride"+r.getOffer());
            }
        }
    }*/