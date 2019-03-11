package iiitb.ess201a7.r16064;

import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;

public class Fleet16065 extends Fleet {
	private int m = 1, x = 0, y = 0;
	private ArrayList<Car16064> c = new ArrayList<Car16064>();
	
	public Fleet16065(String colour) {
		super(16064,colour);
	}

	@Override
	public void addCar(int speed) {
		Car16064 temp = new Car16064(this.getId()*10+m,speed); 
		c.add(temp);
		temp.setLocation(new Location(x,y));
		m++;
		x+=30;
		y+=50;
	}

	@Override
	public Car findNearestCar(Location loc) {
		int minDist = Integer.MAX_VALUE;
		Car r = null;
		for(Car x:c) {
			if(x.distSqrd(loc)<minDist && x.getStatus()==1) {
					r = x;
					minDist = x.distSqrd(loc); 
			}
		}
		return r;
	}
	
	@Override
	public ArrayList<? extends Car> getCars(){
		return c;
	}
}
