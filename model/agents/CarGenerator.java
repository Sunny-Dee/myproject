package myproject.model.agents;

import myproject.model.Model;
import myproject.model.roads.LongRoad;

/**
 * Generates cars on the indicated road and based on the entry rate.
 */

public class CarGenerator implements Agent {
	private LongRoad longRoad;
	private Model model;
	private double carVelocity;
	private double minCarLength;
	private double maxCarLength;
	private double stopDistance;
	private double brakeDistance;
	private double entryRate;

	public CarGenerator(LongRoad longRoad, Model model, double carVelocity, double minCarLength, double maxCarLength,
			double brakeDistance, double stopDistance, double entryRate) {
		this.longRoad = longRoad;
		this.model = model;
		this.carVelocity = carVelocity;
		this.minCarLength = minCarLength;
		this.maxCarLength = maxCarLength;
		this.brakeDistance = brakeDistance;
		this.stopDistance = stopDistance;
		this.entryRate = entryRate;

	}
	
	public boolean hasSpace(){
		double roadLength = longRoad.nextRoad(0).getRoadLength();
		double totalCarLengths = 0;
		for (Car car : longRoad.nextRoad(0).getCars())
			totalCarLengths += car.carLength;
		
		return roadLength > totalCarLengths;
	}

	public void run(double time) {
		if ((time % entryRate) == 0) {
			Car car = new Car(longRoad, model, carVelocity, minCarLength, maxCarLength, brakeDistance, stopDistance);
			model.addAgent(car);
		}
	}

	public boolean isNull() {
		return false;
	}

	public double getDimension() {
		return 0;
	}

	public int getState() {
		return -1;
	}

}
