package myproject.model;

import java.util.List;
import java.util.ArrayList;

/**
 * A road holds cars.
 */
public class Road {
	private double roadLength;
	private static int roadTracker = 0;
	private int roadID;
	public int i;
	public int j; 
	public Agent intersection;
	public boolean horizontal;
	
	public Road(int i, int j, boolean horizontal) { 
		roadLength = 200;
		this.i = i;
		this.j = j;
		roadID = ++roadTracker;
		this.horizontal = horizontal;
	} // Created only by this package. Deliana changed it to public. Change this later
	
	public Road(double roadLength, int i, int j, boolean horizontal) { 
		this.roadLength = roadLength;
		this.i = i;
		this.j = j;
		roadID = ++roadTracker;
		this.horizontal = horizontal;
	}
	
	public double getRoadLength(){
		return roadLength;
	}
	
	public int getRoadID(){
		return roadID;
	}

	private List<Car> cars = new ArrayList<Car>();

	public void accept(Car d) {
		if (d == null) { throw new IllegalArgumentException(); }
		cars.add(d);
	}
	
	public void sinkCar(Car d){
		cars.remove(d);
	}
	
	public List<Car> getCars() {
		return cars;
	}
	
	public void setIntersection(Agent newIntersection){
		intersection = newIntersection;
	}
	
	
	//NOT SURE WHAT TO DO IN SPECIAL CASES WHERE INDEX+1 IS OUT OF BOUND. 
	public double distanceToObstacle(Car car){
		int index = cars.indexOf(car);
		
		return Math.min(car.getPosition() - roadLength, 
				car.getPosition() - cars.get(index + 1).getPosition()); 
	}
	
	public boolean canGo(){
		int s = intersection.getState();
		if (s == -1) return false;
		if (!horizontal){
			if (s <= 1)
				return true;
			else
				return false;
		}
		else{
			if (s <= 1)
				return false;
			else 
				return true;
		}

	}
}
