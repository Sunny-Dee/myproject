package myproject.model.roads;

import java.util.List;

import myproject.model.agents.Agent;
import myproject.model.agents.Car;

import java.util.ArrayList;

/**
 * A road holds cars.
 */
public class Road {
	private static final double OUTOFDISTANCE = 10000000;
	private double roadLength;
	private static int roadTracker = 0;
	private int roadID;
	public Agent intersection;
	public boolean horizontal;

	public Road(double roadLength, boolean horizontal) {
		this.roadLength = roadLength;
		roadID = ++roadTracker;
		this.horizontal = horizontal;
	}

	public double getRoadLength() {
		return roadLength;
	}

	public int getRoadID() {
		return roadID;
	}

	private List<Car> cars = new ArrayList<Car>();

	public void accept(Car d) {
		if (d == null) {
			throw new IllegalArgumentException();
		}
		cars.add(d);
	}

	public void sinkCar(Car d) {
		cars.remove(d);
	}

	public List<Car> getCars() {
		return cars;
	}
	
	public Car getCar(int index){
		return cars.get(index);
	}
	
//	public double carInFront(Car car){
//		int index = cars.indexOf(car) - 1;
//		if (index >= 0)
//			return cars.get(index).getPosition();
//		else
//			return OUTOFDISTANCE;   
//	}
	
	public Car carInFront(Car car){
		int index = cars.indexOf(car) - 1;
		if (index >= 0)
			return cars.get(index);
		else
			return null;
	}

	public void setIntersection(Agent newIntersection) {
		intersection = newIntersection;
	}

	public double distanceToObstacle(Car car) {
		int index = cars.indexOf(car);

		return Math.min(car.getPosition() - roadLength, car.getPosition() - cars.get(index + 1).getPosition());
	}

	public boolean canGo() {
		int s = intersection.getState();
		if (s == -1) {
			return false;
		}
		if (horizontal) {
			return s <= 1;
		} else 
			return s > 1;

	}
}
