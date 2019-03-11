package iiitb.ess201a7.r16037;

import iiitb.ess201a7.a7base.*;
import java.util.*;

public class Fleet16037 extends Fleet {
private ArrayList<Car> car_list=new ArrayList<Car>();
private int car_id=160370;
	public Fleet16037(String colour) {
		super(16037,colour);
	}

	@Override
	public void addCar(int speed) {
   Car temp_car=new Car16037(car_id,speed);
	 car_list.add(temp_car);
	 car_id++;
	}

	@Override

	public Car findNearestCar(Location loc) {
		int index_car=-1;
		double dist,min;
			dist=car_list.get(0).distSqrd(loc);
		min=dist;
		for(int i=0;i<car_list.size();i++)
		{
			dist=car_list.get(i).distSqrd(loc);
			if(dist<=min && car_list.get(i).getStatus()==1)
			{
				min=dist;
				index_car=i;
			}
		}
		if(index_car==-1)
		{
			return null;
   	}
		return car_list.get(index_car);
	}
	public ArrayList<Car> getCars()
	{
		return car_list;
	}
}
