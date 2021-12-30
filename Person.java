package com.company;

public abstract class Person {
    private String mobile;
    private String userName;
    private String email;
    private String password;
    private Status stat=Status.Suspend;
    HandleData memory= Memory.getInstance();


    public Person(){}
    public Person(String mobile, String userName, String email, String password,Status s) {
        this.mobile = mobile;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.stat=s;
    }
    public String getUserName() {
        return userName;
    }
    public void setStat(Status stat) {
        this.stat = stat;
    }
    public String getPassword() {
        return password;
    }
    public abstract boolean register(Person p);
    public boolean login(String user,String pass){
        boolean flag=false,W=false,Y=false;
        for(Person P:memory.getPersons()){
            if (P.getUserName().equals(user) && P.getPassword().equals(pass)) {
                W = true;
                break;
            }
        }
        for (Person u : memory.getPersons()) {
            if (u.userName.equals(user) && u.password.equals(pass)) {
                Y = true;
                if (u.stat.equals(Status.Suspend)) {
                    System.out.println("Sorry your admin suspended you");
                } else {
                    flag = true;
                }
            }
        }

        if(!W)System.out.println("you are in pending list for admin");
        else if(!Y&&W)System.out.println("wrong username or password");
        return flag;
    }
}

