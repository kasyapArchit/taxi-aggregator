package iiitb.ess201a7.r16085;
import java.util.*;
import iiitb.ess201a7.a7base.*;

public class Fleet16085 extends Fleet
{
    String color;
    int p=1;
    ArrayList<Car>cars=new ArrayList<Car>();
    public Fleet16085(String color){
        super(16085,color);
    }


    public void addCar(int speed)
    {
        Car c=new Car16085(160850+p,speed);
        c.setStatus(Car.Idle);
        p++;
        cars.add(c);
    }


    public Car findNearestCar(Location loc)
    {
        int mini=10000;
        Car c=null;

        for(Car c1:cars){
            //System.out.println(i.getStatus());
            if((mini>c1.distSqrd(loc)) && c1.getStatus()==Car.Idle) {
                mini = c1.distSqrd(loc);
                    c = c1;
            }
        }
        //System.out.println(c.getId());
        return c;
    }


    public ArrayList<Car> getCars()
    {
        return cars;
    }
}
