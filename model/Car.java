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
	private double maxVelocity = 6.0;    // The maximum velocity of the car (in meters/second)
	private double distanceToObstacle;
	private double velocity = (int) Math.ceil(Math.random() * maxVelocity);
	private double brakeDistance;  // If distance to nearest obstacle is <= brakeDistance,
	                //   then the car will start to slow down (in meters)
	private double stopDistance;   // If distance to nearest obstacle is == stopDistance,
	                //   then the car will stop (in meters)
	private int length;  // Length of the car (in meters)
	
	private Road currentRoad; 
	private LongRoad longRoad;
	private int index; 
	private boolean sunk = false;


	public Car(double carLength, double maxVelocity, double brakeDistance, LongRoad longRoad) { 
		this.carLength = carLength;
		
		this.maxVelocity = maxVelocity;
		this.brakeDistance = brakeDistance;
		this.longRoad = longRoad;
		index = 0;
		currentRoad = longRoad.nextRoad(index++);
		currentRoad.accept(this);
		
		

	} // Created only by this package
	
	//just to keep the party going here are default values
	public Car(LongRoad longRoad) { 
		carLength = 10;
		maxVelocity = 6.0;
		brakeDistance = 10.0;
		stopDistance = 1.0;
		index = longRoad.isDirectionNSWE() ? longRoad.getRoadSize()-1 : 0 ;
		this.longRoad = longRoad;
		currentRoad = longRoad.isDirectionNSWE() ? longRoad.nextRoad(longRoad.getRoadSize()-1):
												longRoad.nextRoad(index++);
		currentRoad.accept(this);
		
		
	}
	
	//only necessary so bootstrap code compiles
	public Car(Road road) { 
		carLength = 10;
		maxVelocity = 6.0;
		brakeDistance = 10.0;
		stopDistance = 1.0;
		currentRoad = road;
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
		if (longRoad.isDirectionNSWE())
			currentRoad = longRoad.nextRoad(index++);
		else
			currentRoad = longRoad.nextRoad(index--);
		currentRoad.accept(this);
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
	
	
	private void update(){
		setDistanceToObstacle(currentRoad.getRoadLength());
		if (distanceToObstacle < 0){
			velocity = 0;
			//System.out.println("Car sunk.");
		}
		if ((distanceToObstacle + velocity) > brakeDistance)
			velocity = maxVelocity;
		
		else if (distanceToObstacle == stopDistance){
			velocity = 0;
			//System.out.println("Car stopped");
		}
		
		else // (distanceToObstacle <= brakeDistance)
			velocity = 1;
			
			
//			(maxVelocity / (brakeDistance - stopDistance))
//	              * (distanceToObstacle - position);
//		velocity = Math.max(0.0, velocity);
//		velocity = Math.min(maxVelocity, velocity);
	}
	
	public void drive(){
		//position = 0;
		velocity = maxVelocity;
		System.out.println("Car starts at position " + position +
				 " on road " + currentRoad.getRoadID());
		while (velocity!=0){
//			 setDistanceToObstacle(currentRoad.getRoadLength());
			 update();
			 position += velocity;
			 System.out.println("Car is at position " + position +
					 " on road " + longRoad.roadIndex(currentRoad));

		}
		if ((position + carLength + stopDistance) == currentRoad.getRoadLength()){
			System.out.println("Car is at intersection.");
			drivePastIntersection();
		}
	}
	
	public void drivePastIntersection(){
		if (longRoad.carCanGo(currentRoad)){
			System.out.println("This light is green. Car can go");
			//currentRoad = longRoad.nextRoad(index++);
			nextRoad();
		}
		else if (currentRoad.intersection instanceof NullIntersection){
			sunk = true;
			System.out.print("This is a null intersection, so car sunk");
		} else
			System.out.println("Car needs to wait for light to change");
	}
	
	
//	public double updateVelocity(double time, double distanceToObstacle){
//		double newVelocity = (maxVelocity / (brakeDistance - stopDistance))
//              * (distanceToObstacle - position);
//		newVelocity = Math.max(0.0, newVelocity);
//		newVelocity = Math.min(maxVelocity, newVelocity);
////		nextFrontPosition = frontPosition + velocity * timeStep;
//		//once I figure out how the observer pattern I will uncomment and apply this
//		return newVelocity;
//	}

	private boolean isAtIntersection(){
//		double len = currentRoad.getRoadLength(); //* index;
//		double intersectionLen = currentRoad.intersection.getDimension() * index-1;
//		if ((len + intersectionLen) == (position + carLength) ){
//			return true;
//		}
//		return false;
//		return currentRoad.getRoadLength() <= (carLength + segmentPosition + stopDistance + brakeDistance);
		return currentRoad.getRoadLength() == (carLength + segmentPosition);
	}
	public void run(double time) {
		
		if (isAtIntersection()){
			if (currentRoad.intersection.isNull()){
				currentRoad.sinkCar(this);
				color = new java.awt.Color(0);
			}
			if (longRoad.carCanGo(currentRoad)){
				position += currentRoad.intersection.getDimension() + carLength;
				nextRoad();
				
			}
			else{
				segmentPosition +=0;
				position += 0;
			}
		}
		else{
			segmentPosition +=1;
			position += 1;
			System.out.println("Position " + position + "Segment position " + segmentPosition);
		}


	}

	//car should always be able to go. Consider car not being an Agent. 
	public boolean canGo() {
		return true;
	}
}
