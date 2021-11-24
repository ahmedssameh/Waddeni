
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private static ArrayList<Driver> pendingDrivers=new ArrayList<>();
    private static Admin admin;
    HandleData memory=Memory.getInstance();

    private Admin() {}

    public static ArrayList<Driver> getPendingDrivers() {
        return pendingDrivers;
    }

    public static Admin getInstance(){
        if(admin==null){
            admin=new Admin();
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

}

