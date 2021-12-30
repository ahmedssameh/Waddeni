import java.util.ArrayList;

public class AdminDiscount extends Decorator{
    Rides ride;
    AdminDiscount(Rides ride){
        this.ride=ride;
    }
    @Override
    public String getDescription() {
        return "Admin Discount";
    }

    @Override
    double cost() {
            int f=0;
        for(String S: Admin.getDiscountPlaces()){
            if (ride.getDestination().equalsIgnoreCase(S)){
                f=1;break;
            }
        }
        if(f==1) return super.cost()-(0.1*super.cost());
        else return super.cost();
    }
}
