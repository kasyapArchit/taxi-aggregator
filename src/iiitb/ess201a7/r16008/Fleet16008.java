package iiitb.ess201a7.r16008;

import iiitb.ess201a7.a7base.*;
import java.util.*;

public class Fleet16008 extends Fleet {



private ArrayList<Car16008> carList;
private int count;
public Fleet16008(String colour) {
        super(16008,colour);
        carList=new ArrayList<Car16008>();
        count=0;
}

@Override
public void addCar(int speed) {

        int x1=(int)(Math.random()*1000);
        int y1=(int)(Math.random()*1000);
        Car16008 car=new Car16008(Integer.parseInt(Integer.toString(getId())+ Integer.toString(++count)),speed);
        car.setLocation(new Location(x1,y1));
        carList.add(car);
}

@Override
public Car findNearestCar(Location loc) {
        int x1=loc.getX();
        int y1=loc.getY();
        double leastDistance=Double.POSITIVE_INFINITY;
        Car closestCar=null;
        for(Car c: carList) {
                if(c.getStatus()==Car.Booked || c.getStatus()==Car.OnTrip) continue;
                int x2=c.getLocation().getX();
                int y2=c.getLocation().getY();
                double distance=Math.sqrt( (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
                if(distance<leastDistance ) {
                        closestCar=c;
                        leastDistance=distance;
                }
        }

        return closestCar;
}

@Override
public ArrayList<Car16008> getCars(){
        return carList;
}
}
