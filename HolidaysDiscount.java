import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class HolidaysDiscount extends Decorator{
    Rides ride;
    Set<LocalDate> holidays;

    public void InsertAllHoliday(){
        holidays.add(LocalDate.parse("2021/12/29"));

    }
    HolidaysDiscount(Rides ride){
        this.ride=ride;
    }
    @Override
    public String getDescription() {
        return "Holidays Discount";
    }

    @Override
    double cost() {
        int f=0;
        InsertAllHoliday();
        for (LocalDate LD:holidays){
            if(ride.getDate().equals(LD)){f=1;break;}
        }

        if(f==1)return super.cost()-(0.05*super.cost());
        else return super.cost();
    }
}
