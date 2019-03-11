package iiitb.ess201a7.r16124;

import iiitb.ess201a7.a7base.*;

import java.util.ArrayList;

public class Fleet16124 extends Fleet {
	ArrayList<Car> cars;
	public Fleet16124(String colour) {
		super(16124,colour);
		cars=new ArrayList<>();
	}


	@Override
	public void addCar(int speed) {
		cars.add(new Car16124(this.getId()*100+cars.size()+1,speed));
	}

	@Override
	public Car findNearestCar(Location loc) {
        Car best=null;
		for (Car x:cars) {
			if(x.getStatus()==1)
			{										//sets best initially to the first idle car
				best=x;
				break;
			}
		}
        if(best==null)return null;
		for (Car x:cars) {
			if(x.distSqrd(loc)<best.distSqrd(loc) && x.getStatus()==1)		//selects the best idle car according to distances
			{
				best=x;
			}
		}
		//System.out.println(best.distSqrd(loc));
		return best;
	}

	@Override
	public ArrayList<? extends Car> getCars() {
		return cars;					//returns all cars in the fleet
	}
}
