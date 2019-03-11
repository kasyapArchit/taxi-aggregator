package iiitb.ess201a7.r16105;
//import static java.lang.Math.sqrt;
import iiitb.ess201a7.a7base.*;
import java.util.ArrayList;

public class Fleet16105 extends Fleet {
	 private ArrayList<Car> car_list=new ArrayList<Car>();
	 //private Car c;
	 private int index1;
	public Fleet16105(String colour) {
		super(16105,colour);
		index1=0;
	}

  @Override
	public void addCar(int speed){
		//int id=;
		Car16105 car=new Car16105(Integer.parseInt(Integer.toString(getId())+ Integer.toString(++index1)),speed);
		//Car car = new Car16105(id,speed);
		car_list.add(car);


	}

	public double getdistance(Location a,Location b){
		float ans;
    ans=((a.getX()-b.getX())*(a.getX()-b.getX()))+((a.getY()-b.getY())*(a.getY()-b.getY()));
    return Math.sqrt(ans);
	}

  @Override
	public Car findNearestCar(Location loc) {
	  int car=0,count=0;
		double time,min=0;
		Car temp;
		for(int i=0;i<car_list.size();i++)
		{
			temp=car_list.get(i);

			time=Math.sqrt(temp.distSqrd(loc))/temp.getSpeed();
			if(temp.getStatus()==1)
			{
			if(count==0)
			{
				min=time;
				car=i;
				count++;
			}
			else if(count!=0 && min>=time)
			{
				min=time;
				car=i;
			}
		}
		}
		if(car_list.get(car).getStatus()==1){
			return car_list.get(car);
		}
		return null;
	}



  public ArrayList<Car> getCars(){
	  return car_list;
  }
}
