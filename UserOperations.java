package com.hawm.hawm.controler;
import com.hawm.hawm.model.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserOperations {
    LoginServices obj = new LoginServices();
    Rides current_ride = null;
    Memory memory = Memory.getInstance();

    @PostMapping(value = "CreateRide")
    public void CreateRide(@RequestParam(name = "source") String source,
                           @RequestParam(name = "destination") String destination,
                           @RequestParam(name = "num") int numPass) {
        current_ride = ((User) obj.getCurrentu()).createRide(source, destination, numPass);
    }
    @PutMapping(value = "SetRate")
    public String SetRate(@RequestParam(name = "rate") int rate) {
        for (Rides r : memory.getRides()) {
            for(User u:r.Details.getUser()) {
                if (r.Details.getDriver() != null && u.equals(obj.getCurrentu()) && !r.Details.isEnded()) {
                    ((User) obj.getCurrentu()).setRate(r.Details.getDriver(), rate);
                    r.Details.setEnded(true);
                }
            }
        }
        return "there is no more rides for you to rate";
    }
    @GetMapping(value = "ShowRates")
    public ArrayList<String> showRates() {
        int flage = 0;
        ArrayList<String> DriversAverageRate=new ArrayList<>();
        for (Rides r : memory.getRides()) {
            for (User u : r.Details.getUser()) {
                if (u.equals(obj.getCurrentu()) && r.Details.getDriver() != null) {
                    flage = 1;
                    float Avragerate = ((User) obj.getCurrentu()).ShowavrageRate(r.Details.getDriver());
                    DriversAverageRate.add("Driver username " + r.Details.getDriver().getUserName() + " His average rate " + Avragerate);
                }
            }
        }
        if (flage == 0) return null;
        else return DriversAverageRate;
    }
    @GetMapping(value = "listOfOffers")
    public Map<String, Double> listOfOffers() {
        Map<String,Double> userOffer=new HashMap<>();
        for(Map.Entry<Driver, Double> T : current_ride.getOffers().entrySet()){
            for(User u:current_ride.Details.getUser()) {
                if(u.equals(obj.getCurrentu()))
                userOffer.put(T.getKey().getUserName(), T.getValue());
            }
        }
        return userOffer;
    }
    @PutMapping(value = "ChoosingRide")
    public void ChoosingRide(@RequestParam(name = "drivername") String name) {
        for (Map.Entry<Driver, Double> T : current_ride.getOffers().entrySet()) {
            if (T.getKey().getUserName().equals(name)) {
                current_ride.Details.setDriver(T.getKey());
                if(current_ride.Details.getUser().size()==current_ride.getNumberOfPassengers())
                current_ride.setOffer(T.getValue());
            }
        }
    }
    @GetMapping(value = "ShowDiscountOffers")
    public String ShowDiscountedOffers(){
            for(Rides r:memory.getRides()){
                for(User u:r.Details.getUser()){
                    if(u.equals(obj.getCurrentu())){
                        return "price"+r.getOffer();
                    }else return "not found";
                }
            }
       return "Error";
    }
}
