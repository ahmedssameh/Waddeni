
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Admin extends Observer {
    private static ArrayList<Driver> pendingDrivers=new ArrayList<>();
    private static Set<String> Events;

    private static Admin admin;
    HandleData memory=Memory.getInstance();


    private Admin(Rides subject) {this.subject=subject;
        this.subject.subscribers(this);
    }
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
    public static Admin getInstance2(Rides subject){
        if(admin==null){
            admin=new Admin(subject);
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
    @Override
    public void update() {
        String event;
        if(this.subject.isISArrivedDistination()){
            for(int i=0;i<this.subject.getUser().size();i++) {
                event = "Captain Arrived this ride to source " + " Ride name " + this.subject.getIdRide() + "current time " + this.subject.getNow()
                        + "Captain " + this.subject.getDriver().getUserName() + "User " + this.subject.getUser().get(i).getUserName();
                Events.add(event);
            }
        }
        if(this.subject.isISArrivedSource()){
            for(int i=0;i<this.subject.getUser().size();i++) {
                event = "Captain Arrived this ride to source " + " Ride name " + this.subject.getIdRide() + "current time " + this.subject.getNow()
                        + "Captain " + this.subject.getDriver().getUserName() + "User " + this.subject.getUser().get(i).getUserName();
                Events.add(event);
            }
        }
        if(!this.subject.getOffers().isEmpty()){
            for(Map.Entry<Driver, Double> T : this.subject.getOffers().entrySet()){
                if(T.getKey().equals(this.subject.getDriver())){
                    event="Captain Sets offer "+" Ride name "+this.subject.getIdRide()+" current time "+this.subject.getNow()
                            +" Captain "+this.subject.getDriver().getUserName()+" with offer "+this.subject.getOffer();
                    Events.add(event);
                }
            }
        }
        if(this.subject.getOffer()!=-1){
            for(int i=0;i<this.subject.getUser().size();i++) {
                event = "User accept ride " + " Ride name " + this.subject.getIdRide() + " current time " + this.subject.getNow()
                        + " User " + this.subject.getUser().get(i).getUserName();
                Events.add(event);
            }
        }
    }
}

