public class BirthdayDiscount extends Decorator {
    Rides ride;
    BirthdayDiscount(Rides ride){
        this.ride=ride;
    }
    @Override
    public String getDescription() {
        return "Birthday Discount";
    }

    @Override
    double cost() {
        int f=0;
        for (User u:ride.getUser()){
            if(u.birthday.equals(ride.getDate())){f=1;break;}
        }
        if(f==1)return super.cost()-(0.1*super.cost());
        else return super.cost();
    }
}
