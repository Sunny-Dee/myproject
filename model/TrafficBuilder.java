package myproject.model;
//import java.util.concurrent.ThreadLocalRandom;

public class TrafficBuilder {
	//TODO set default values for everything. 

	public TrafficBuilder(){};
	
//	Simulation time step (seconds) [0.1]
	private double timeStep = 0.1;
//			Simulation run time (seconds) [1000]
	private double time = 1000;
//			Grid size (number of roads) [row = 2, column 3 
	private int rows = 2;
	private int columns = 3;
//			Traffic pattern [simple]
	private int pattern = 1;
//			Car entry rate (seconds/car) [min = 1.0, max = 2.5]
	private double entryRate = 1.0; //ThreadLocalRandom.current().nextDouble(1.0, 2.5 + 1) ; 
//			Road segment length (meters) [min = 10.0, max = 15.0]
	private double roadSegmentLength = 10; 
//			Intersection length (meters) [min = 10.0, max  = 15.0]
	private double intersectionLength = 10;
//			Car length (meters) [min = 10.0, max  = 15.0]
	private double carLength = 10;
//			Car maximum velocity (meters/second) [min = 1.0, max = 3.0]  
	private double maxVelocity = 1;
//			Car stop distance (meters) [min = 0.5, max = 5.0]
	private double stopDistance = 1.0;
	private double breakDistance = 9.0; //[min = 9.0, max = 10.0]
//			Traffic light green time (seconds) [min = 30.0, max = 180.0]
	private double greenLightTime = 90.0;
//			Traffic light yellow time (seconds) [min = 32.0, max = 40.0]
	private double yellowLightTime = 32;
	
	
	public void setTimeStep(double newtime){ timeStep = newtime;}
	
	public void settime(double newtime){ time = newtime;}	
	
	public void setGrid(int newRows, int newColumns){
		rows = newRows;
		columns = newColumns;
	}
	
	public boolean setPattern(int patternChoice){ //consider design for this one
		if (patternChoice < 1 || patternChoice > 2)
//			throw new IllegalArgumentException();
			return false;
		pattern = patternChoice;
		return true; //if setPattern == false, ask again. 
	}
	
	public String patternToString(){
		if (pattern == 1)
			return "[simple]";
		else
			return "[alternating]";
	}
	
	public boolean setEntryRate(double newEntryRate){
		if (newEntryRate < 1.0 || newEntryRate > 2.5)
			return false;
		entryRate = newEntryRate;
		return true;
	}
	
	public boolean setRoadSegmentLength(double newLength){
		if (newLength < 10.0 || newLength > 15.0)
			return false;
		roadSegmentLength = newLength;
		return true;
	}
	
	public boolean setIntersectionLength(double newLength){
		if (newLength < 10.0 || newLength > 15.0)
			return false;
		intersectionLength = newLength;
		return true;
	}
	
	public boolean setCarLength(double newCarLength){
		if (newCarLength < 10.0 || newCarLength > 15.0)
			return false;
		carLength = newCarLength;
		return true;
	}
	
	public boolean setMaxVelocity(double newMax){
		if (newMax < 10.0 || newMax > 15.0)
			return false;
		maxVelocity = newMax;
		return true;
	}
	
	public boolean setStopDistance(double newStopDist){
		if (newStopDist < 0.5 || newStopDist > 5.0)
			return false;
		stopDistance = newStopDist;
		return true;
	}
	
	public boolean setBreakDistance(double newBreakDist){
		if (newBreakDist < 9.0 || newBreakDist > 10.0)
			return false;
		breakDistance = newBreakDist;
		return true;
	}
	
	public boolean setGreenLight(double greenTime){
		if (greenTime < 30.0 || greenTime > 180.0)
			return false;
		greenLightTime = greenTime;
		return true;
	}
	
	public boolean setYellowLight(double yellowTime){
		if (yellowTime < 32.0 || yellowTime > 40.0)
			return false;
		yellowLightTime = yellowTime;
		return true;
	}
	
	
	public String toString(){
		StringBuilder str = new StringBuilder("");
		str.append("1. Simulation time step (seconds) [" + timeStep + "] \n");
		str.append("2. Simulation run time (seconds) [" + time + "] \n");
		str.append("3. Grid size (number of roads) [rows = " + rows + ", columns " + columns + "\n");
		str.append("4. Traffic pattern" + patternToString() + "\n");
		str.append("5. Car entry rate (seconds/car) [min = 1.0, max = 2.5]\n");
		str.append("6. Road segment length (meters) [min = 10.0, max = 15.0]\n");
		str.append("7. Intersection length (meters) [min = 10.0, max  = 15.0]\n");
		str.append("8. Car length (meters) [min = 10.0, max  = 15.0]\n");
		str.append("9. Car maximum velocity (meters/second) [min = 1.0, max = 3.0]\n");
		str.append("10. Car stop distance (meters) [min = 0.5, max = 5.0]\n");
		str.append("11. Car break distance (meters) [min = 9.0, max = 10.0]\n");
		str.append("12. Traffic light green time (seconds) [min = 30.0, max = 180.0]\n");
		str.append("13. Traffic light yellow time (seconds) [min = 32.0, max = 40.0]\n");
		
		return str.toString();
	}
	
}
