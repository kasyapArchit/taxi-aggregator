package iiitb.ess201a7.r16105;
//import java.util.*;
import java.lang.Math;
import iiitb.ess201a7.a7base.*;


class Car16105 extends Car {

	private int status;
	//private double sin,cos,tan;
	 private Location location_obj,a,b;
	 private Trip trip_obj;
	 private double dist2,dist;
	public Car16105(int fid,int speed) {
		super(fid,speed);

		status=1;
	}

	public void setLocation(Location l) {
		//  Auto-generated method stub

		location_obj =l;
	}
	@Override
	public Location getLocation() {
		//  Auto-generated method stub
	  return location_obj;
	}

	@Override
	public void setStatus(int s) {
		// Auto-generated method stub
		  status=s;
	}

	@Override
	public int getStatus() {
		//  Auto-generated method stub
		return status;
	}
	@Override
	public int distSqrd(Location f){
		double ans;
    ans=((location_obj.getX()-f.getX())*(location_obj.getX()-f.getX()))+((location_obj.getY()-f.getY())*(location_obj.getY()-f.getY()));
    return (int)ans;
	}
	@Override

	public void assignTrip(Trip trip) {
		//  Auto-generated method stub
		trip_obj=trip;
		a=trip_obj.getStart();
		b=trip_obj.getDest();
		setStatus(Car.Booked);
	}

	@Override
	public Location getStart() {
		//  Auto-generated method stub
		return a;
	}

	@Override
	public Location getDest() {
		//  Auto-generated method stub
		return b;
	}

	@Override
	public void updateLocation(double deltaT){

		   dist=(getSpeed()*deltaT);
		   if(status==2){
			     dist2=Math.pow(location_obj.getX()-a.getX(),2)+Math.pow(location_obj.getY()-a.getY(),2);
			    dist2=Math.sqrt(dist2);
			    if (dist<=dist2)
			   {

			    	location_obj.set((int)((a.getX()-location_obj.getX())*(dist/dist2)+location_obj.getX()),(int)((a.getY()-location_obj.getY())*(dist/dist2)+location_obj.getY()));
			   }


			   else
			   {
			     status=3;
			    }
			   }
			  else if(status==3){
			   dist2=Math.pow(location_obj.getX()-b.getX(),2)+Math.pow(location_obj.getY()-b.getY(),2);
			   dist2=Math.sqrt(dist2);
			   if (dist<=dist2)
			   {
				   location_obj.set((int)((b.getX()-location_obj.getX())*(dist/dist2)+location_obj.getX()),(int)((b.getY()-location_obj.getY())*(dist/dist2)+location_obj.getY()));
			   }

			   else{
			     status=1;
			    }

			 }
			 }



	public Trip getTrip(){
		return trip_obj;
	}


}
