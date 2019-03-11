package iiitb.ess201a7.r16064;

import iiitb.ess201a7.a7base.*;

class Car16065 extends Car {
	private Location loc;
	private int stat;
	private Trip trip;
	private double px,py;
	
	public Car16065(int fid,int speed) {
		super(fid, speed);
		stat = Idle;
	}

	@Override
	public void setLocation(Location l) {
		loc = l;
	}

	@Override
	public Location getLocation() {
		return loc;
	}

	@Override
	public void setStatus(int s) {
		stat = s;
	}

	@Override
	public int getStatus() {
		return stat;
	}

	@Override
	public int distSqrd(Location loc) {
		int d = (int) Math.sqrt(Math.pow(this.loc.getX()-loc.getX(), 2)+Math.pow(this.loc.getY()-loc.getY(), 2));
		return d;
	}
	@Override
	public void assignTrip(Trip trip) {
		this.trip = trip;
		px = loc.getX();
		py = loc.getY();
		setStatus(Car.Booked);
	}

	@Override
	public Trip getTrip() {
		return trip;
	}
	
	@Override
	public Location getStart() {
		return trip.getStart();
	}

	@Override
	public Location getDest() {
		return trip.getDest();
	}

	@Override
	public void updateLocation(double deltaT) {
		if(this.getStatus()==Idle) 
			return ;
		else if(this.getStatus()==Booked) {
			/*if(Math.pow(deltaT*maxSpeed,2) >= Math.pow(this.distSqrd(trip.getStart()),2)) {
				this.setStatus(OnTrip);
				this.loc = this.getStart();
				return;
			}
			double dist = Math.sqrt(Math.pow(this.loc.getX()-this.getStart().getX(), 2)+Math.pow(this.loc.getY()-this.getStart().getY(), 2));
			double cos = (this.getStart().getX()-this.loc.getX())/dist;
			double sin = (this.getStart().getY()-this.loc.getY())/dist;
			int fx = (int) (this.maxSpeed*deltaT*cos + this.loc.getX());
			int fy = (int) (this.maxSpeed*deltaT*sin + this.loc.getY());
			this.setLocation(new Location(fx,fy));
			//if(fx==this.getStart().getX()) 
				//this.setStatus(OnTrip);*/
			if(Math.pow(deltaT*maxSpeed,2)>=(Math.pow(px-trip.getStart().getX(), 2))+Math.pow(py-trip.getStart().getY(), 2)) {
				px = trip.getStart().getX();
				py = trip.getStart().getY();
				this.loc = this.getStart();
				this.setStatus(OnTrip);
				return;
			}
			double dist = Math.sqrt(Math.pow(px-this.getStart().getX(), 2)+Math.pow(py-this.getStart().getY(), 2));
			double cos = (this.getStart().getX()-px)/dist;
			double sin = (this.getStart().getY()-py)/dist;
			px = this.maxSpeed*deltaT*cos + px;
			py = this.maxSpeed*deltaT*sin + py;
			this.setLocation(new Location((int) px, (int) py));
		}
		else if(this.getStatus()==OnTrip) {
			/*if(Math.pow(deltaT*maxSpeed,2) >= Math.pow(this.distSqrd(trip.getDest()),2)) {
				this.setStatus(Idle);
				this.loc = this.getDest();
				return;
			}
			double dist = Math.sqrt(Math.pow(this.loc.getX()-this.getDest().getX(), 2)+Math.pow(this.loc.getY()-this.getDest().getY(), 2));
			double cos = (this.getDest().getX()-this.getStart().getX())/dist;
			double sin = (this.getDest().getY()-this.getStart().getY())/dist;
			int fx = (int) (this.maxSpeed*deltaT*cos + this.loc.getX());
			int fy = (int) (this.maxSpeed*deltaT*sin + this.loc.getY());
			this.setLocation(new Location(fx,fy));
			//if(fx==this.getDest().getX())
				//this.setStatus(Idle);*/
			if(Math.pow(deltaT*maxSpeed,2)>=(Math.pow(px-trip.getDest().getX(), 2))+Math.pow(py-trip.getDest().getY(), 2)) {
				px = trip.getDest().getX();
				py = trip.getDest().getY();
				this.loc = this.getDest();
				this.setStatus(Idle);
				return;
			}
			double dist = Math.sqrt(Math.pow(px-this.getDest().getX(), 2)+Math.pow(py-this.getDest().getY(), 2));
			double cos = (this.getDest().getX()-px)/dist;
			double sin = (this.getDest().getY()-py)/dist;
			px = this.maxSpeed*deltaT*cos + px;
			py = this.maxSpeed*deltaT*sin + py;
			this.setLocation(new Location((int) px, (int) py));
		}
	}
}
