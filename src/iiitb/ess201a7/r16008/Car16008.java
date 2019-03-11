package iiitb.ess201a7.r16008;

import iiitb.ess201a7.a7base.*;

class Car16008 extends Car {

    private Location currentLocation;
    private Trip currentTrip;
    private int status;
	public Car16008(int fid, int speed) {
        super(fid,speed);
        setStatus(Idle);
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
        currentLocation=l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return currentLocation;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
        status=s;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public Trip getTrip() {
		// TODO Auto-generated method stub
		return currentTrip;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
        currentTrip=trip;
        setStatus(Booked);


	}

    @Override
	public int distSqrd(Location loc) {
		return (this.currentLocation.getX()-loc.getX())*(this.currentLocation.getX()-loc.getX()) + (this.currentLocation.getY()-loc.getY())*(this.currentLocation.getY()-loc.getY());
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		return currentTrip.getStart();
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		return currentTrip.getDest();
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
        int x2=getLocation().getX();
        int y2=getLocation().getY();
        int x1,y1;
        if(getStatus()==OnTrip){
             x1=getDest().getX();
             y1=getDest().getY();

        }
        else if(getStatus()==Booked){
             x1=getStart().getX();
             y1=getStart().getY();
        }
        else{
            return;
        }
        double length=Math.sqrt( (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        if(getStatus()==Booked && length<=getSpeed()*deltaT){
            setStatus(OnTrip);
            System.out.println("Car:"+getId()+" has reached the pickup Point.");
            // deltaT=(int)(length/(getSpeed()*deltaT))+5;
        }
        else if(getStatus()==OnTrip && length <=getSpeed()*deltaT){

            setStatus(Idle);
            System.out.println("Car:"+getId()+" has reached the destination Point and trip has been completed.");
            // deltaT=(int)(length/(getSpeed()*deltaT))+5;
        }
        if(length!=0)currentLocation.set((int)(x2+(deltaT)*getSpeed()*(x1-x2)/length),(int)(y2+(deltaT)*getSpeed()*(y1-y2)/length));
	}




}
