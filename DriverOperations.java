package com.hawm.hawm.controler;
import com.hawm.hawm.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;
@RestController
public class DriverOperations {
    LoginServices obj = new LoginServices();
    Rides ride_wanted = null;
    Memory memory = Memory.getInstance();

    @PutMapping(value = "addFavoritesArea")
    String addFavoritesArea(@RequestParam(name = "FavoriteArea") String FavoriteArea) {
        ((Driver) obj.getCurrentd()).addFavoritesArea(FavoriteArea);
        return obj.getCurrentd().getUserName() + "Add new favorite Area " + FavoriteArea;
    }
    @PostMapping(value = "OfferRide")
    String OfferRide(@RequestParam(name = "id") int id, @RequestParam(name = "money") double money) {
        for (Rides R : memory.getRides()) {
            if (R.getIdRide() == id) ride_wanted = R;
        }
        if (ride_wanted == null) {
            return "Wrong id";
        } else {
            ((Driver) obj.getCurrentd()).setMoney(ride_wanted, money);
            return obj.getCurrentd().getUserName() + "offer ride id " + id + " by " + money;
        }
    }
    @GetMapping(value = "ShowRate")
    ArrayList<String> ShowRate() {
        ArrayList<String> Rates = new ArrayList<>();
        for (Map.Entry<User, Double> T : ((Driver) obj.getCurrentd()).getUsersrate().entrySet()) {
            Rates.add(T.getKey().getUserName() + ": " + T.getValue());
        }
        return Rates;
    }
    @GetMapping(value = "RidesInFavoriteArea")
    ArrayList<Rides> RidesInFavoriteArea() {
        ArrayList<Rides> Rides = new ArrayList<>();
        for (String Area : ((Driver) obj.getCurrentd()).getFavorite_area()) {
            for (Rides ri : memory.getRides()) {
                if (Area.equals(ri.Details.getDestination()) && !ri.Details.isEnded()) {
                    Rides.add(ri);
                }
            }
        }
        return Rides;
    }
    @PutMapping(value = "ArrivalInSourceRide")
    String ArrivalInSourceRide(@RequestParam(name = "id") int id) {
        for (Rides R : memory.getRides()) {
            if (R.getIdRide() == id) ride_wanted = R;
        }
        ride_wanted.setISArrivedSource(true);
        return obj.getCurrentd().getUserName()+"is arrived in source";
    }
    @PutMapping(value = "ArrivalInDestinationRide")
    String ArrivalInDestinationRide(@RequestParam(name = "id") int id) {
        for (Rides R : memory.getRides()) {
            if (R.getIdRide() == id) ride_wanted = R;
        }
        ride_wanted.setISArrivedDistination(true);
        ride_wanted.Details.setEnded(true);
        ride_wanted.Details.getDriver().setStatus(true);
        return obj.getCurrentd().getUserName()+"is arrived in Destination";
    }
}

