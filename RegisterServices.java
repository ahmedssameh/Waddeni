package com.hawm.hawm.controler;
import com.hawm.hawm.model.Driver;
import com.hawm.hawm.model.Person;
import com.hawm.hawm.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterServices {
    Person driver=new Driver();
    Person user=new User();
    @PostMapping(value = "createDriver")
    public Person createDriver(@RequestParam String mobile, @RequestParam String userName, @RequestParam String email,
                             @RequestParam String password, @RequestParam int license, @RequestParam int nationalId){
        driver=new Driver(mobile,userName,email,password,license,nationalId);
        driver.register(driver);
        return driver;

    }
    @PostMapping(value = "createUser")
    public Person createUser(@RequestParam String mobile, @RequestParam String userName, @RequestParam String email,
                             @RequestParam String password){
        user=new User(mobile,userName,email,password);
        user.register(user);
        return user;
    }

}
