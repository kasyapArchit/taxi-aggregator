package iiitb.ess201a7.r16037;

import iiitb.ess201a7.a7base.*;
//import java.lang.*;
class Car16037 extends Car {


		public Car16037(int id,int speed) {
			super(id,speed);

	}
//private int incrementor=1;
private Location loc_car;
private Location start_loc;
private Location dest_loc;

private int status_car=Idle;
public int distSqrd(Location loc)
{
	int x=loc.getX(),y=loc.getY();
	int a=loc_car.getX();
	int	b=loc_car.getY();
	int	dist=(int)Math.pow(x-a,2)+(int)Math.pow(y-b,2);
return dist;
}
	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		loc_car=l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return loc_car;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
		status_car=s;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status_car;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		start_loc=trip.getStart();
		dest_loc=trip.getDest();
		status_car=Booked;
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		if(status_car!=Idle)
		{
			return start_loc;
		}
		return null;
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		if(status_car!=Idle)
		{
			return dest_loc;
		}
		return null;
	}
	public  Trip getTrip()
	{
		Trip car_trip=new Trip(start_loc,dest_loc);
		return car_trip;
	}
	@Override

	public void updateLocation(double deltaT){
		// TODO Auto-generated method stub
		// System.out.println("car:"+getId()+" "+loc_car.getX()+" "+loc_car.getY());
		// System.out.println("start:"+start_loc.getX()+" "+start_loc.getY());
		// System.out.println("dest:"+dest_loc.getX()+" "+dest_loc.getY());

		if(status_car==Booked)
		{
			double D=Math.sqrt(Math.pow((start_loc.getX()-loc_car.getX()),2)+Math.pow((start_loc.getY()-loc_car.getY()),2));
			double sin=(start_loc.getY()-loc_car.getY())/D;
			double cos=(start_loc.getX()-loc_car.getX())/D;
			double dist=maxSpeed*deltaT;

			Location previous=new Location(loc_car.getX(),loc_car.getY());

			 loc_car.set((loc_car.getX()+(int)Math.ceil(dist*cos)),(loc_car.getY()+(int)Math.ceil(dist*sin)));
    if(loc_car.getX()>1000)
		{
			loc_car.set(1000,loc_car.getY());
		}
    else if(loc_car.getX()<0)
		{
			loc_car.set(0,loc_car.getY());
		}
		else if(loc_car.getY()>1000)
		{
			loc_car.set(loc_car.getY(),1000);
		}
		else if(loc_car.getY()<0)
		{
			loc_car.set(loc_car.getY(),0);
		}
		 if((loc_car.getX()<=start_loc.getX() && previous.getX()>start_loc.getX())||(loc_car.getY()<=start_loc.getY() && previous.getY()>start_loc.getY()))
		 { loc_car.set(start_loc.getX(),start_loc.getY());
			 status_car=OnTrip;
		 }
		 else if((loc_car.getX()>=start_loc.getX() && previous.getX()<start_loc.getX())||(loc_car.getY()>=start_loc.getY() && previous.getY()<start_loc.getY()))		 {
			 loc_car.set(start_loc.getX(),start_loc.getY());
			 status_car=OnTrip;
		 }
		 else if((loc_car.getX()>=start_loc.getX() && previous.getX()<start_loc.getX())||(loc_car.getY()<=start_loc.getY() && previous.getY()>start_loc.getY()))		 {
			 loc_car.set(start_loc.getX(),start_loc.getY());

			status_car=OnTrip;
		}
		else if((loc_car.getX()<=start_loc.getX() && previous.getX()>start_loc.getX())||(loc_car.getY()>=start_loc.getY() && previous.getY()<start_loc.getY()))		 {
			loc_car.set(start_loc.getX(),start_loc.getY());

		 status_car=OnTrip;
	 }
	}
	else if(status_car==OnTrip)
	{
		double D=Math.sqrt(Math.pow((dest_loc.getX()-loc_car.getX()),2)+Math.pow((dest_loc.getY()-loc_car.getY()),2));
		double sin=(dest_loc.getY()-loc_car.getY())/D;
		double cos=(dest_loc.getX()-loc_car.getX())/D;
		double dist=maxSpeed*deltaT;
		Location previous=new Location(loc_car.getX(),loc_car.getY());

		 loc_car.set((loc_car.getX()+(int)Math.ceil(dist*cos)),(int)(loc_car.getY()+(int)Math.ceil(dist*sin)));
		 if(loc_car.getX()>1000)
 		{
 			loc_car.set(1000,loc_car.getY());
 		}
     else if(loc_car.getX()<0)
 		{
 			loc_car.set(0,loc_car.getY());
 		}
 		else if(loc_car.getY()>1000)
 		{
 			loc_car.set(loc_car.getX(),1000);
 		}
 		else if(loc_car.getY()<0)
 		{
 			loc_car.set(loc_car.getX(),0);
 		}
	 if((loc_car.getX()<=dest_loc.getX() && previous.getX()>dest_loc.getX())||(loc_car.getY()<=dest_loc.getY() && previous.getY()>dest_loc.getY()))
	 { loc_car.set(dest_loc.getX(),dest_loc.getY());
		 status_car=Idle;
	 }
	 else if((loc_car.getX()>=dest_loc.getX() && previous.getX()<dest_loc.getX())||(loc_car.getY()>=dest_loc.getY() && previous.getY()<dest_loc.getY()))	 {
		 loc_car.set(dest_loc.getX(),dest_loc.getY());
		 status_car=Idle;
	 }
	 else if((loc_car.getX()>=dest_loc.getX() && previous.getX()<dest_loc.getX())||(loc_car.getY()<=dest_loc.getY() && previous.getY()>dest_loc.getY()))		 {
		 loc_car.set(dest_loc.getX(),dest_loc.getY());
		status_car=Idle;
	}
	else if((loc_car.getX()<=dest_loc.getX() && previous.getX()>dest_loc.getX())||(loc_car.getY()>=dest_loc.getY() && previous.getY()<dest_loc.getY()))		 {
		loc_car.set(dest_loc.getX(),dest_loc.getY());
	 status_car=Idle;
 }
}
	}
}
