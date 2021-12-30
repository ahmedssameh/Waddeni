package com.company;
public class AverageDriverRate {
    float calculateAvgRate(Driver driver){
        float sum=0;int count=0;
        for(double d:driver.getUsersrate().values()){
            count++;
            sum+=d;
        }
        driver.setAveragerate(sum/count);
        return sum/count;

    }
}
