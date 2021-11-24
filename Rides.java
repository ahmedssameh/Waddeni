
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rides {
    private static int id=0;
    private int idRide;
    private String source;
    private String destination;
    private double offer;
    private boolean ended=false;
    private User user=null;
    private Driver driver=null;
    private Map<Driver,Double> offers=new HashMap<>();



    Rides(){
        this.idRide= id++;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setOffer(double offer) {
        this.offer = offer;
    }
    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public Driver getDriver() {
        return driver;
    }
    public User getUser() {
        return user;
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


}

