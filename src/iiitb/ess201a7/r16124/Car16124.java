package iiitb.ess201a7.r16124;

import iiitb.ess201a7.a7base.*;

//import static java.lang.Math.random;
import static java.lang.Math.pow;

class Car16124 extends Car {
	private Location l;
	private int status;
	private Trip trip;
	private static int r=2;					//to generate some random location of our car


	public Car16124(int id,int speed) {
		super(id,speed);
		l=new Location(r*3,r*2);   //assuming starting location
		r=r+4;
		trip=new Trip(l,l);
		this.status=1;
	}

	@Override
	public void setLocation(Location l) {
		this.l=l;
	}

	@Override
	public Location getLocation() {
		return l;
	}

	@Override
	public void setStatus(int s) {
		status=s;
		//System.out.println("status changed! "+ this.getId()+" "+getStatus());
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void assignTrip(Trip trip) {
		//this.status=2;
		//System.out.println("Trip Assigned!!");
        setStatus(Booked);
		this.trip=trip;
	}

	@Override
	public Trip getTrip() {
		return trip;
	}


	//both getStart and getDest return null if car is not booked
	@Override
	public Location getStart() {
		if(this.status!=1) {
			//System.out.println("lolololol"+ trip.getStart().getX());
			return trip.getStart();
		}
		else
			return null;
	}

	@Override
	public Location getDest() {
		if(this.status!=1)
			return trip.getDest();
		else
		{
			//System.out.println("********"+ trip.getStart().getY());
			return null;
		}
	}


	@Override
	public void updateLocation(double deltaT) {
		if(getStatus()==1) {
			//System.out.println("watt");
			return;                                 //(x3,y3) is the place where we will reach after deltaT time
		}											//(x1,y1) is our location
													//(x2,y2) is destination location
		float x2=0,y2=0;
		if(getStatus()==2){
			//System.out.println("Entering 2");
			x2=trip.getStart().getX();				//setting destination according to if we are on trip or on our way to pickup
			y2=trip.getStart().getY();
		}
		if(getStatus()==3){
			//System.out.println("Entering 3");
			x2=trip.getDest().getX();
			y2=trip.getDest().getY();
		}


		//System.out.println(x2);
		//System.out.println(y2);
		double x1=l.getX(),y1=l.getY(),d=deltaT*super.getSpeed();
		double D = pow(pow(x2-x1,2)+pow(y2-y1,2),0.5),sin=(y2-y1)/D,cos=(x2-x1)/D;
		double rd=D-d,x3=(x2-cos*rd),y3=(y2-sin*rd);
		//System.out.println(x3);
		//System.out.println(y3);
		l.set((int) x3,(int) y3);
		if((y2-y1)*(y2-y3)<=0){							//this checks if we have reached our destination or not and changes status
			l.set((int)x2,(int)y2);
			if(status==3){
				setStatus(1);
			}
			if(status==2){
				setStatus(3);
			}
		}
	}


	@Override
	public int distSqrd(Location loc) {
		return ((loc.getX()-l.getX())*(loc.getX()-l.getX()))+((loc.getY()-l.getY())*(loc.getY()-l.getY()));
	}

}
