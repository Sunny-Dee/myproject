package myproject.model;


/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */
public class Car implements Agent {
	
	public double carLength;
	private double position;
	private double segmentPosition;
	private java.awt.Color color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));
	private double maxVelocity = 3.0;    // The maximum velocity of the car (in meters/second)
	private double distanceToObstacle;
//	private double velocity = (int) Math.ceil(Math.random() * maxVelocity);
	private double brakeDistance;  // If distance to nearest obstacle is <= brakeDistance,
	                //   then the car will start to slow down (in meters)
	private double stopDistance;   // If distance to nearest obstacle is == stopDistance,
	                //   then the car will stop (in meters)
	private int length;  // Length of the car (in meters)
	
	private Road currentRoad; 
	private LongRoad longRoad;
	private int index; 
	private boolean sunk = false;
	private double distanceToNextCar = 10000000; //something really high before more cars come along
	private Model2 model;

	public Car(LongRoad longRoad, Model2 model, double maxVelocity, 
			double carLength, double brakeDistance, double stopDistance) { 
		this.carLength = carLength;
		this.maxVelocity = maxVelocity;
		this.brakeDistance = brakeDistance;
		this.stopDistance = stopDistance;
		this.model = model;
		index = longRoad.isDirectionNSWE() ? longRoad.getRoadNum()-1 : 0 ;
		this.longRoad = longRoad;
		currentRoad = longRoad.isDirectionNSWE() ? longRoad.nextRoad(longRoad.getRoadNum()-1):
												longRoad.nextRoad(index++);
		currentRoad.accept(this);
	}
	
	public double getPosition() {
		return position;
	}
	
	public double getDimension(){
		return carLength;
	}
	
	public boolean isNull(){
		return false;
	}
	
	public Road currentRoad(){
		return currentRoad;
	}
	
	public void nextRoad(){
//		currentRoad.sinkCar (this);
		if (longRoad.isDirectionNSWE())
			currentRoad = longRoad.nextRoad(Math.min(index++ , longRoad.getRoadNum()-1));
		else
			currentRoad = longRoad.nextRoad(Math.max(index--, 0));
		
//		if (index > longRoad.getRoadNum()-1 || index < 0) {
//			model.removeAgent(this);
//		} 
//		else {
//			currentRoad.accept(this);
//		}
		
		segmentPosition = 0;
	}
	
	public void setDistanceToObstacle(double obstacle){
		distanceToObstacle = obstacle - position - carLength;
	}
	
	public java.awt.Color getColor() {
		return color;
	}
	
	public boolean carSunk(){
		return sunk;
	}
	
	private double update(){
		distanceToObstacle = (Math.min(currentRoad.getRoadLength() - segmentPosition - carLength,
				distanceToNextCar));
		
		double velocity = (maxVelocity / (brakeDistance - stopDistance))
	            * (distanceToObstacle - stopDistance);
		
		velocity = Math.max(0.0, velocity);
		velocity = Math.min(maxVelocity, velocity);
		
		return velocity;
	}
	
	
	private boolean isAtIntersection(){
		return currentRoad.getRoadLength() <= (carLength + segmentPosition);
	}
	
	
	public void run(double time) {
		
		if (isAtIntersection()){
			if (currentRoad.intersection.isNull()){
				currentRoad.sinkCar(this);
				position +=0;
			}
			else if (longRoad.carCanGo(currentRoad)){ // && ((index < longRoad.getRoadNum()) || (index != 0))){
				position += currentRoad.intersection.getDimension() + carLength;
				nextRoad();
				
			}
			else{
				segmentPosition +=0;
				position += 0;
			}
		}
		else{
			double v = update();
			segmentPosition +=2;
			position += 2;
		}
	}
	
	public int getState() {
		//Auto-generated method stub
		return 0;
	}
}
