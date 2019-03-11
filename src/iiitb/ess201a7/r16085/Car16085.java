package iiitb.ess201a7.r16085;
import java.util.Random;
import iiitb.ess201a7.a7base.*;
public class Car16085 extends Car{
    int speed;

    Location l1;
    int p;
    int id;
    Trip t1;
    double distance;
    Random rn = new Random();
    int answer = rn.nextInt(10) + 1;
    public Car16085(int id,int speed){
        super(id,speed);
        l1=new Location(0,0);
    }
    public int get_speed(){
        return speed;
    }
    public void setLocation(Location l){
        this.l1=l;
    }
    public  Location getLocation(){
        return this.l1;
    }
    public int distSqrd(Location loc) {
        distance=(Math.pow((loc.getX()-l1.getX()),2)+Math.pow((loc.getY()-l1.getY()),2));
        return (int)distance;
    }
    public  void setStatus(int s){
        this.p=s;
    }

    public  int getStatus(){
        return this.p;
    }

    public  void assignTrip(Trip trip){
            this.t1=trip;
    }

    public Trip getTrip(){
        return t1;
    }
    public Location getStart(){
        return t1.getStart();
    }
    public Location getDest() {
        return t1.getDest();
    }
    public void updateLocation(double deltaT) {

        if(getStatus()==1)
        {
            return;
        }
        Location l2=  getStart();
        Location l3 = getDest();
        double x,y;
        Double d1 = Math.sqrt(Math.pow(l1.getX()-l2.getX(),2)+Math.pow(l1.getY()-l2.getY(),2));
        Double d2 = Math.sqrt(Math.pow(l2.getX()-l3.getX(),2)+Math.pow(l2.getY()-l3.getY(),2));
        Double d3= Math.sqrt(Math.pow(l1.getX()-l3.getX(),2)+Math.pow(l1.getY()-l3.getY(),2));
        if(getStatus()==2)
        {
            if(d1 > (deltaT*getSpeed( )))
            {
                x = l1.getX()+((l2.getX()-l1.getX())/d1)*(deltaT*getSpeed());
                y = l1.getY()+((l2.getY()-l1.getY())/d1)*(deltaT*getSpeed());
                Location req = new Location((int)x,(int)y);
                l1 = req;
                return;
            }
            else
            {

                setStatus(3);
                deltaT = deltaT - d1/getSpeed();
                l1=l2;
                return ;

            }
        }

        x = l1.getX()+((l3.getX()-l1.getX())/d3)*(deltaT*getSpeed());
        y = l1.getY()+((l3.getY()-l1.getY())/d3)*(deltaT*getSpeed());
        System.out.println(x+","+y+" "+l3.getX()+" "+l3.getY());
        Location req = new Location((int)x,(int)y);
        l1 = req;
        if(d3<deltaT*getSpeed()){
    			setStatus(1);
    			l1=l3;
    		}
    }

}
