package com.company;
import java.util.ArrayList;

public class Memory implements HandleData{
    static ArrayList<Person> persons=new ArrayList<>();
    static ArrayList<Rides> rides=new ArrayList<>();
    private static Memory memory;
    private Memory(){};
    public static Memory getInstance(){
        if(memory==null){
            memory=new Memory();
        }
        return memory;
    }
    @Override
    public ArrayList<Rides> getRides() {return rides;}
    @Override
    public  ArrayList<Person> getPersons() {
        return persons;
    }
}
