package com.hawm.hawm.controler;
import com.hawm.hawm.model.Admin;
import com.hawm.hawm.model.Driver;
import com.hawm.hawm.model.Person;
import com.hawm.hawm.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginServices {
    static Person currentu = new User();
    static Person currentd=new Driver();
    @GetMapping(value = "currr")
    public Person getCurrentd() {
        return currentd;
    }
    @GetMapping(value = "curr")
    public Person getCurrentu() {
        return currentu;
    }

    @GetMapping(value = "LoginPerson")
    public Person LoginPerson(@RequestParam(name = "user") String username
            , @RequestParam(name = "pass") String password) {
        currentu = currentu.login(username, password);
        if(currentu instanceof User) return currentu;
        else { currentd=currentu;//currentd=currentd.login(username, password);
            return currentd;}
    }

    @GetMapping(value = "LoginAdmin")
    public boolean LoginAdmin(@RequestParam(name = "AdminName") String AdminName
            , @RequestParam(name = "pass") String pass) {
        Admin admin = Admin.getInstance();
        return AdminName.equals(admin.getAdmin_user_name())
                && pass.equals(admin.getAdmin_password());
    }
}

