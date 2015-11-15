package myproject.model.agents;

import myproject.model.Model;
import myproject.model.roads.LongRoad;
import myproject.model.roads.Road;

/**
 * A car remembers its position from the beginning of its road. Cars have random
 * velocity and random length within the indicated range. 
 */
public class Car implements Agent {

	public double carLength;
	private double position;
	private double segmentPosition;
	private static final double OUTOFDISTANCE = 10000000;
	private java.awt.Color color = new java.awt.Color((int) Math.ceil(Math.random() * 255),
			(int) Math.ceil(Math.random() * 255), (int) Math.ceil(Math.random() * 255));
	private double maxVelocity; 
	private double distanceToObstacle;
	private double brakeDistance; 
	private double stopDistance; 
	private int length; 

	private Road currentRoad;
	private LongRoad longRoad;
	private int index;
	private boolean sunk = false;
	private Model model;
	private Car carAhead;

	public Car(LongRoad longRoad, Model model, double maxVelocity, double carLength, double brakeDistance,
			double stopDistance) {
		this.carLength = (int) Math.ceil(Math.random() * carLength);
		this.maxVelocity =  (int) Math.ceil(Math.random() * maxVelocity);
		this.brakeDistance = brakeDistance;
		this.stopDistance = stopDistance;
		this.model = model;
		index = 0;
		this.longRoad = longRoad;
		currentRoad = longRoad.nextRoad(index++);
		currentRoad.accept(this);
		carAhead = currentRoad.carInFront(this);
	}

	public double getPosition() {
		return position;
	}

	public double getDimension() {
		return carLength;
	}

	public boolean isNull() {
		return false;
	}

	public Road currentRoad() {
		return currentRoad;
	}

	public void nextRoad() {

//		currentRoad.sinkCar(this);
		currentRoad = longRoad.nextRoad(Math.min(index++, longRoad.getRoadNum() - 1));
//		currentRoad.accept(this);
		segmentPosition = 0;
	}
	
	public double distanceToObstacle(){
		double carToCar;
		if (carAhead == null)
			carToCar = OUTOFDISTANCE;
		else
			carToCar = carAhead.getPosition() - position - carLength;
		
		double carToIntersection = currentRoad.getRoadLength() - segmentPosition - carLength;
		
		return Math.min(carToIntersection, carToCar);
		
	}

	public java.awt.Color getColor() {
		return color;
	}

	public boolean carSunk() {
		return sunk;
	}

	private double update() {
		distanceToObstacle = distanceToObstacle();
		double velocity;
		
		if (distanceToObstacle > brakeDistance)
			velocity = maxVelocity;
		else {
			if(distanceToObstacle <= stopDistance)
				velocity = 0;
			else
				velocity = 1;
		}		

		return velocity;
	}

	private boolean isAtIntersection() {
		return currentRoad.getRoadLength() <= (carLength + segmentPosition + stopDistance);
	}

	public void run(double time) {

		if (isAtIntersection()) {
			// if (sunk){
			// position +=100000;
			// currentRoad.sinkCar(this);}
			if (currentRoad.intersection.isNull()) {
				currentRoad.sinkCar(this);
				position += 100000;
				model.removeAgent(this);
				sunk = true;
			} 
			
			if (currentRoad.canGo()) { 
				position += currentRoad.intersection.getDimension() + carLength;
				nextRoad();

			}  else {
				segmentPosition += 0;
				position += 0;

			}
		} else {
			double v = update();
			segmentPosition += v;
			position += v;
		}
	}

	public int getState() {
		// Auto-generated method stub
		return 0;
	}
}
