public class FirstRideDiscount extends Decorator {
    Rides ride;
    FirstRideDiscount(Rides ride){
        this.ride=ride;
    }
    @Override
    public String getDescription() {
        return "First Ride Discount";
    }

    @Override
    double cost() {
        int f=0;
        for(User u:ride.getUser()) {
            if (u.IsFirstRide){
                u.IsFirstRide=false;
                f=1;
                break;}
        }
        if(f==1)return super.cost()-(0.1*super.cost());
        else return super.cost();
    }
}
