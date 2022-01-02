package com.hawm.hawm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
//@CrossOrigin("*")
public class User extends Person {
    //@Autowired
    LocalDate birthday=LocalDate.now();
    public boolean IsFirstRide=true;
    public User(){}
    public User(@JsonProperty String mobile, @JsonProperty String userName,@JsonProperty String email,
                @JsonProperty String password) {
        super(mobile, userName, email, password, Status.valueOf("Active"));
    }
    @GetMapping(value="register")
    public boolean register(@RequestBody Person p){
        if(memory.getPersons().contains(p)){
            System.out.println("User is already registered");
            return false;
        }else {
            memory.getPersons().add(p);
            return true;
        }
    }
    @PostMapping(value = "creatRide")
    public Rides createRide( @RequestParam(name="source") String source, @RequestParam(name="destination") String destination, @RequestParam(name="numPass")  int numPass){
        int f=0;
        if(numPass>1) {
            for (Rides r : memory.getRides()) {
                if (r.Details.getDestination().equalsIgnoreCase(destination) &&
                        r.Details.getSource().equalsIgnoreCase(source) &&
                        r.getNumberOfPassengers() == numPass&&r.Details.availableRide()) {
                    r.Details.setUser(this);
                    if(r.getNumberOfPassengers()==r.Details.getUser().size()) r.Details.setavailableRide(false);
                    return r;
                }
            }
        }
        if(numPass>=1) {
            Rides r = new Rides();
            r.subscribers(Admin.getInstance());
            r.Details.setSource(source);
            r.Details.setDestination(destination);
            r.Details.setUser(this);
            r.setNumberOfPassengers(numPass);
            if(numPass==1) {r.Details.setavailableRide(false);}
            memory.getRides().add(r);
            return r;
        }
        return null;
    }
    @PutMapping(value="setRate")
    public void setRate(@RequestBody Driver D, @RequestParam(name="rate")double rate){
        D.getUsersrate().put(this,rate);
    }
    @GetMapping(value="showavgrate")
    public float ShowavrageRate(@RequestBody Driver D){
        return D.getAveragerate();
    }
}

