package com.hawm.hawm.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class Person {
    private String mobile;
    private String userName;
    private String email;

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    private String password;
    private Status stat=Status.Suspend;
    HandleData memory= Memory.getInstance();
    public Person(){};
    public Person(@JsonProperty String mobile,@JsonProperty String userName, @JsonProperty String email,
                  @JsonProperty String password, @JsonProperty
                          Status s) {
        this.mobile = mobile;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.stat=s;
    }
    @GetMapping(value = "getusername")
    public String getUserName() {
        return userName;
    }
    @PutMapping(value = "setstat")
    public void setStat(@RequestBody Status stat) {
        this.stat = stat;
    }
    @GetMapping(value="getpass")
    public String getPassword() {
        return password;
    }
    public abstract boolean register(Person p);
    @GetMapping(value = "login")
    public Person login(@RequestBody String user,@RequestBody String pass){
        boolean flag=false,W=false,Y=false;
        Person requiredPerson=null;
        for(Person P:memory.getPersons()){
            if (P.getUserName().equals(user) && P.getPassword().equals(pass)) {
                W = true;requiredPerson=P;
                if (!P.stat.equals(Status.Suspend)) {Y=true; flag=true;}
            }
            }
        if(!W||!Y)System.out.println("please ReEnter username or password or you are in pending list");
        if (flag)return requiredPerson;
        else return null;
    }
}


