package com.hawm.hawm.controler;

import com.hawm.hawm.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@RestController
public class AdminOperations {
    Admin admin=Admin.getInstance();
    HandleData memory = Memory.getInstance();
    @GetMapping(value = "PendingDrivers")
    ArrayList<Driver> PendingDrivers(){
        return Admin.getPendingDrivers();
    }
    @GetMapping(value="AcceptDriver")
    public String AcceptDriver(@RequestParam(name = "username")String DriverUserName){
            Driver driver_wanted = null;

            for (Driver D : Admin.getPendingDrivers()) {
                if (D.getUserName().equals(DriverUserName)) {
                    driver_wanted = D;
                }
            }
            if(driver_wanted==null){
                return "wrong username";
            }
            else{
                admin.verifyDriver(driver_wanted);
                return driver_wanted.getUserName()+" is verified";
            }
        }
    @PutMapping (value="SuspendPerson")
     String SuspendPerson(@RequestParam(name = "username")String UserName){
        Person person_wanted=null;
         for (Person person : memory.getPersons()) {
             if (person.getUserName().equals(UserName)) {
                 person_wanted = person;
             }
         }
         if(person_wanted==null){
             return "wrong username for person ";
         }
         else { admin.suspend(person_wanted);
             return person_wanted.getUserName()+" is Suspended";}
     }
     @PutMapping(value = "AddDiscountPlace")
    String DiscountArea(@RequestParam(name = "Place")String Place){
        admin.addDiscountPlaces(Place);
        return "Admin add discount place"+Place;
    }
     @GetMapping(value="getEvents")
     Set<String> getEvents(){
             return Admin.getEvents();
        }
        @PostMapping(value = "discountPlaces")
        void discountPlaces(@RequestParam(name = "place") String place){
            Admin.getDiscountPlaces().add(place);
        }
}
