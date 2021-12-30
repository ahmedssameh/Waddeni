
package com.company;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Main {

    public static Person getPerson(String username,String pass){
        HandleData memory= Memory.getInstance();
        Person requiredPerson=null;
        for(Person p:memory.getPersons()){
            if(p.getUserName().equals(username)&&p.getPassword().equals(pass)){
                requiredPerson=p;
            }
        }
        return requiredPerson;

    }
    public static void userMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("                 User menu");
        System.out.println("1-create Ride");
        System.out.println("2-set rate for driver");
        System.out.println("3-check the average rating for the driver.");
        System.out.println("4-Show Offers and Choose Driver.");
        System.out.println("5-log out");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter number an operation you want choice ");

    }
    public static void driverMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("                Driver menu");
        System.out.println("1-Add favorite Area");
        System.out.println("2-list all rides with source area");
        System.out.println("3-suggest a price to this ride");
        System.out.println("4-list user ratings.");
        System.out.println("5-List Rides matching your favorite area.");
        System.out.println("6-Arrived Source ride");
        System.out.println("7-Arrived Distination ride");
        System.out.println("8-log out");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter number an operation you want choice ");

    }
    public static void adminMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("               Admin menu     ");
        System.out.println("1-list all pending driver registrations");
        System.out.println("2-verify driver");
        System.out.println("3-Suspend user or driver");
        System.out.println("4-Show log file for Events");
        System.out.println("5-log out");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter number an operation you want choice ");
    }
    public static void mainMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("                   Main menu");
        System.out.println("1-Register");
        System.out.println("2-login");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter number an operation you want choice ");
    }
    public static void main(String[] args) {
        Person currentPerson=null;
        boolean is_admin=false;
        String admin_user_name="HAWM";
        String admin_password="HAWM2021-2022";
        Scanner input = new Scanner(System.in);
        HandleData memory = Memory.getInstance();
        Admin admin = Admin.getInstance();

        boolean verify_login=false;
        User temp=new User();
        while (true) {
            mainMenu();
            int num_operation = input.nextInt();
            //Registration
            if (num_operation == 1) {
                System.out.println("-------------------------------------");
                System.out.println("1-Driver");
                System.out.println("2-User");
                System.out.println("-------------------------------------");
                int type_person = input.nextInt();
                if (type_person == 1) {
                    while (true) {
                        System.out.println("Enter username");
                        String username = input.next();
                        System.out.println("Enter mobile");
                        String mobile = input.next();
                        System.out.println("Enter email");
                        String email = input.next();
                        System.out.println("Enter password");
                        String password = input.next();
                        System.out.println("Enter license");
                        int license = input.nextInt();
                        System.out.println("Enter nationalId");
                        int nationalId = input.nextInt();
                        Person person = new Driver(mobile, username, email, password, license, nationalId);
                        boolean verify = person.register(person);
                        if (verify) break;
                    }
                }
                else if (type_person == 2) {
                    while (true) {
                        System.out.println("Enter username");
                        String username = input.next();
                        System.out.println("Enter mobile");
                        String mobile = input.next();
                        System.out.println("Enter email");
                        String email = input.next();
                        System.out.println("Enter password");
                        String password = input.next();
                        Person person = new User(mobile, username, email, password);
                        boolean verify = person.register(person);
                        if (verify) break;
                    }
                }
            }
            //Login
            else if (num_operation == 2) {
                System.out.println("Enter username");
                String name = input.next();
                System.out.println("Enter password");
                String password = input.next();
                verify_login = temp.login(name, password);
                if (verify_login) {
                    currentPerson = getPerson(name, password);
                }
            }
            //admin
            else if (num_operation == 19) {
                while (true) {
                    System.out.println("Enter username");
                    String name = input.next();
                    System.out.println("Enter password");
                    String password = input.next();
                    if (name.equals(admin_user_name) && password.equals(admin_password)) {
                        verify_login = true;
                        is_admin = true;
                    }
                    if (verify_login) break;
                }
            }
            else System.out.println("please choice operation from 1 and 2");

            //admin operation
            if (is_admin && verify_login) {
                while (true) {
                    adminMenu();
                    int operation = input.nextInt();
                    if (operation == 1) {
                        for (Driver D : Admin.getPendingDrivers()) {
                            System.out.println("-------------------------------------");
                            System.out.println("username: " + D.getUserName());
                            System.out.println("password: " + D.getPassword());
                            System.out.println("License: " + D.getLicense());
                            System.out.println("NationalId: " + D.getNationalId());
                            System.out.println("---------------------------------------------------------");
                        }
                    }
                    else if (operation == 2) {
                        Driver driver_wanted = null;
                        System.out.println("Enter username");
                        String name = input.next();
                        for (Driver D : Admin.getPendingDrivers()) {
                            if (D.getUserName().equals(name)) {
                                driver_wanted = D;
                            }
                        }
                        if(driver_wanted==null){
                            System.out.println("wrong username");
                        }
                        else admin.verifyDriver(driver_wanted);
                    }
                    else if (operation == 3) {
                        Person person_wanted = null;
                        System.out.println("Enter username");
                        String name = input.next();
                        for (Person person : memory.getPersons()) {
                            if (person.getUserName().equals(name)) {
                                person_wanted = person;
                            }
                        }
                        if(person_wanted==null){
                            System.out.println("wrong username for person ");
                        }
                        else  admin.suspend(person_wanted);
                    }
                    else if (operation==4){
                        System.out.println("-----------------AllEvents---------------------------");

                        for (String S:Admin.getEvents()){
                            System.out.println(S);
                        }
                    }
                    else if (operation == 5) {
                        is_admin=false;
                        verify_login=false;
                        break;
                    }
                    else {
                        System.out.println("please choice operation from 1 to 4");
                    }
                }
            }
            //User operation
            else if (currentPerson instanceof User&&verify_login) {
                while (true) {
                    userMenu();
                    Rides current_ride = null;
                    int operation = input.nextInt();
                    if (operation == 1) {
                        System.out.println("Enter source");
                        String source = input.next();
                        System.out.println("Enter destination");
                        String destination = input.next();
                        current_ride = ((User) currentPerson).createRide(source, destination,1);
                    }
                    else if (operation == 2) {
                        for(Rides r:memory.getRides()){
                            if(r.getDriver() != null &&r.getUser().equals((User) currentPerson)&&!r.isEnded()){
                                System.out.println(r.getDriver().getUserName());
                                System.out.println(r.getIdRide());
                                System.out.println("Do you want to rate this ride? y/n");
                                String Choice=input.next();
                                if(Choice.equals("y")){
                                    System.out.println("Enter rate for this ride from 1 to 5");
                                    double rate = input.nextDouble();
                                    ((User) currentPerson).setRate(r.getDriver(), rate);
                                    r.setEnded(true);
                                }
                                else if(Choice.equals("n")){
                                    continue;
                                }else System.out.println("Enter valid choice");
                            }
                        }
                        System.out.println("there is no more rides for you to rate");
                    }
                    else if (operation == 3) {
                        int flage=0;
                        for(Rides r:memory.getRides()){
                            if(r.getUser().equals(currentPerson)&&r.getDriver()!=null){
                                flage=1;
                                float Avragerate = ((User) currentPerson).ShowavrageRate(r.getDriver());
                                System.out.println("Driver username "+r.getDriver().getUserName());
                                System.out.println("His average rate "+Avragerate);
                            }
                        }
                        if (flage==0)System.out.println("I can't find driver for this ride");
                    }
                    else if(operation==4){
                        int flag=0;
                        for(Rides r:memory.getRides()){
                            for(User u:r.getUser()){
                            if(u.equals(currentPerson)&&!r.isEnded()){
                                for(Map.Entry<Driver, Double> T : r.getOffers().entrySet()){
                                    System.out.println(T.getKey().getUserName());
                                    System.out.println(T.getKey().getAveragerate());
                                    System.out.println(T.getValue());
                                    System.out.println("Are you Okay with this Offer? y/n");
                                    String Choice=input.next();
                                    if(Choice.equals("y")){
                                        r.setDriver(T.getKey());
                                        r.setOffer(T.getValue());
                                        flag=1;
                                        break;
                                    }
                                    else if(Choice.equals("n")) {
                                        continue;
                                    }
                                    else System.out.println("Enter valid Choice");
                                }
                            }
                        }
                        }

                        if(flag==0) System.out.println("there no more rides matching you right now");
                    }
                    else if (operation == 5) {
                        verify_login = false;
                    }
                    if (!verify_login) break;
                }
            }
            //driver Operation
            else if (currentPerson instanceof Driver&&verify_login) {
                while (true) {
                    driverMenu();
                    ArrayList<Rides> list_rides = new ArrayList<>();
                    int operation = input.nextInt();
                    if (operation == 1) {
                        System.out.println("Enter Favorite Area");
                        String Area = input.next();
                        ((Driver) currentPerson).addFavoritesArea(Area);
                    }
                    else if (operation == 2) {
                        System.out.println("Enter Area want need list rides");
                        String Area = input.next();
                        list_rides = ((Driver) currentPerson).showRidesForSpecificArea(Area);
                        if(list_rides==null){System.out.println("There is no ride matching the area you chose");}
                        else {
                            for (Rides R : list_rides) {
                                System.out.println("id: " + R.getIdRide());
                                System.out.println("source: " + R.getSource());
                                System.out.println("destination: " + R.getDestination());
                                System.out.println("---------------------------------------------------------");
                            }
                        }

                    }
                    else if (operation == 3) {
                        System.out.println("Enter id ride you wanted");
                        int id = input.nextInt();
                        Rides ride_wanted = null;
                        for (Rides R : memory.getRides()) {
                            if (R.getIdRide() == id) ride_wanted = R;
                        }
                        if(ride_wanted==null){
                            System.out.println("Wrong id");
                        }
                        else {
                            System.out.println("Enter money need offer ride ");
                            double money = input.nextDouble();
                            ((Driver) currentPerson).setMoney(ride_wanted, money);
                        }
                    }
                    else if (operation == 4) {
                        Map<User, Double> UR = ((Driver) currentPerson).getUsersrate();
                        if(UR==null){
                            System.out.println("there is no user rate list");
                        }
                        else {
                            for (Map.Entry<User, Double> T : UR.entrySet()) {
                                System.out.println(T.getKey().getUserName() + ": " + T.getValue());
                                System.out.println("---------------------------------------------------------");
                            }
                        }
                    }
                    else if(operation==5){
                        int f=0;
                        for(String r:((Driver) currentPerson).getFavorite_area()){
                            for(Rides ri:memory.getRides()){
                                if(r.equals(ri.getDestination())&&!ri.isEnded()){
                                    f=1;
                                    System.out.println("id: " + ri.getIdRide());
                                    System.out.println("source: " + ri.getSource());
                                    System.out.println("destination: " + ri.getDestination());
                                    System.out.println("---------------------------------------------------------");
                                }
                            }
                        }
                        if(f==0)System.out.println("no ride matching your favorite area ");

                    }
                    else if(operation==6){
                        System.out.println("Enter id ride you wanted");
                        int id = input.nextInt();
                        Rides ride_wanted = null;
                        for (Rides R : memory.getRides()) {
                            if (R.getIdRide() == id) ride_wanted = R;
                        }
                        if(ride_wanted==null){
                            System.out.println("Wrong id");
                        }
                        else {
                            ((Driver) currentPerson).ArrivedSource(ride_wanted);

                        }
                    }
                    else if(operation==7){
                        System.out.println("Enter id ride you wanted");
                        int id = input.nextInt();
                        Rides ride_wanted = null;
                        for (Rides R : memory.getRides()) {
                            if (R.getIdRide() == id) ride_wanted = R;
                        }
                        if(ride_wanted==null){
                            System.out.println("Wrong id");
                        }
                        else {
                            ((Driver) currentPerson).ArrivedDistination(ride_wanted);

                        }
                    }
                    else if (operation == 8) {
                        verify_login = false;
                    }
                    if (!verify_login) break;
                }
            }
        }
    }
}
