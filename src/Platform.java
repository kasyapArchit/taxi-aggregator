import java.util.ArrayList;
import iiitb.ess201a7.a7base.*;

public class Platform {
	private ArrayList<Fleet> f = new ArrayList<Fleet>();

	public Platform() {

	}

	public void addFleet(Fleet f) {
		this.f.add(f);
	}

	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) {
		Car c=null;
		int minDst = Integer.MAX_VALUE;
		for(Fleet i:f) {
			Car temp = i.findNearestCar(trip.getStart());
			if(temp==null) {/*System.out.println("oh my");*/}
			else if(temp.distSqrd(trip.getStart())<minDst) {
				c = temp;
				minDst = temp.distSqrd(trip.getStart());
			}
		}
		if(c==null) {
			System.out.println("No cars available");
			return null;
		}
		c.setStatus(Car.Booked);
		c.assignTrip(trip);
		return c;
	}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() {
		ArrayList<Car> c = new ArrayList<Car>();
		for(Fleet i:f) {
			c.addAll(i.getCars());
		}
		return c;
	}
}
