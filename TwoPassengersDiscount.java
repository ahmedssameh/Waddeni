public class TwoPassengersDiscount extends Decorator {
    Rides ride;
    TwoPassengersDiscount(Rides ride){
        this.ride=ride;
    }
    @Override
    public String getDescription() {
        return "Two Passenger Discount";
    }

    @Override
    double cost() {
        if(ride.getUser().size()==2) return super.cost()-(0.05*super.cost());
        else return super.cost();
    }
}
