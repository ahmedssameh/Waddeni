
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver extends Person implements Observer,Subject{
    private int license;
    private int nationalId;
    private float averagerate;
    private Map<User,Double> usersrate=new HashMap();
    private ArrayList<String> favorite_area=new ArrayList<>();
    ArrayList<Observer> observersUser =new ArrayList<>();
    Driver(){}
    public Driver(String mobile, String userName, String email, String password,int license,int nationalId) {
        super(mobile, userName, email, password,Status.valueOf("Suspend"));
        this.license=license;
        this.nationalId=nationalId;
    }
    public boolean  register(Person d){
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
            if(r.getDestination().equalsIgnoreCase(predestination)){
                DriverRides.add(r);
            }
        }
        return DriverRides;
    }

    public void setAveragerate(float averagerate) {
        this.averagerate = averagerate;
    }
    public void setMoney(Rides r,double moneypay){
        r.getOffers().put(this,moneypay);
        notifyAllObserver();
    }
    //when driver is observer for User subject
    @Override
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
    }

    @Override
    public void unSubscribers(Observer observer) {
        observersUser.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer O : observersUser) {
            O.update();
        }
    }

}

