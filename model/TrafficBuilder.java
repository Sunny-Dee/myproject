package myproject.model;

/**
 * Class to hold default values and allow users change the parameters. 
 * It runs a new model with the indicated parameters. 
 */

public class TrafficBuilder {
	private AnimatorBuilder builder;
	private Model model;


	private double timeStep = 1.0;
	private double time = 1000;
	private int rows = 2;
	private int columns = 3;
	private int pattern = 2;
	private double entryRate = 25.0; 
	private double roadSegmentLength = 200;
	private double intersectionLength = 10;
	private double minCarLength = 10;
	private double maxCarLength = 15;
	private double maxVelocity = 6.0;
	private double stopDistance = 1.0;
	private double breakDistance = 10.0;
	private double greenLightTime = 50.0;
	private double yellowLightTime = 30.0;

	public TrafficBuilder(AnimatorBuilder builder) {
		this.builder = builder;
		
	};

	public void setTimeStep(double newtime) {
		timeStep = newtime;
	}

	public double timeStep() {
		return timeStep;
	}

	public void settime(double newtime) {
		time = newtime;
	}

	public double time() {
		return time;
	}

	public void setGrid(int newRows, int newColumns) {
		rows = newRows;
		columns = newColumns;
	}

	public int rows() {
		return rows;
	}

	public int columns() {
		return columns;
	}

	public boolean setPattern(int patternChoice) { // consider design for this
													// one
		if (patternChoice < 1 || patternChoice > 2)
			// throw new IllegalArgumentException();
			return false;
		pattern = patternChoice;
		return true; // if setPattern == false, ask again.
	}

	public String patternToString() {
		if (pattern == 1)
			return "[simple]";
		else
			return "[alternating]";
	}

	public boolean pattern() {
		return pattern == 1;
	}

	public boolean setEntryRate(double newEntryRate) {
		if (newEntryRate < 1.0 || newEntryRate > 4.0)
			return false;
		entryRate = newEntryRate * 10;
		return true;
	}

	public double entryRate() {
		return entryRate;
	}

	public boolean setRoadSegmentLength(double newLength) {
		if (newLength < 100.0 || newLength > 200.0)
			return false;
		roadSegmentLength = newLength;
		return true;
	}

	public double roadSegmentLen() {
		return roadSegmentLength;
	}

	public boolean setIntersectionLength(double newLength) {
		if (newLength < 10.0 || newLength > 20.0)
			return false;
		intersectionLength = newLength;
		return true;
	}

	public double intersectionLen() {
		return intersectionLength;
	}

	public boolean setCarLength(double newMinCarLength, double newMaxCarLength) {
		if (newMinCarLength < 0.0 || newMinCarLength > 100.0)
			return false;
		if (newMaxCarLength < 0.0 || newMaxCarLength > 100.0)
			return false;
		minCarLength = newMinCarLength;
		maxCarLength = newMaxCarLength;
		return true;
	}
	
	public double minCarLen() {
		return minCarLength;
	}
	
	public double maxCarLen() {
		return maxCarLength;
	}

	public boolean setMaxVelocity(double newMax) {
		if (newMax < 1.0 || newMax > 6.0)
			return false;
		maxVelocity = newMax;
		return true;
	}

	public double maxVel() {
		return maxVelocity;
	}

	public boolean setStopDistance(double newStopDist) {
		if (newStopDist < 0.5 || newStopDist > 5.0)
			return false;
		stopDistance = newStopDist;
		return true;
	}

	public double stopDist() {
		return stopDistance;
	}

	public boolean setBreakDistance(double newBreakDist) {
		if (newBreakDist < 9.0 || newBreakDist > 10.0)
			return false;
		breakDistance = newBreakDist;
		return true;
	}

	public double breakDist() {
		return breakDistance;
	}

	public boolean setGreenLight(double greenTime) {
		if (greenTime < 30.0 || greenTime > 180.0)
			return false;
		greenLightTime = greenTime;
		return true;
	}

	public double greenLightTime() {
		return greenLightTime;
	}

	public boolean setYellowLight(double yellowTime) {
		if (yellowTime < 32.0 || yellowTime > 40.0)
			return false;
		yellowLightTime = yellowTime;
		return true;
	}

	public double yellowLightTime() {
		return yellowLightTime;
	}

	public void runModel() {
		model = new Model(builder, this);
		model.run(time, timeStep);
		model.dispose();
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("1. Simulation time step (seconds) [" + timeStep + "] \n");
		str.append("2. Simulation run time (seconds) [" + time + "] \n");
		str.append("3. Grid size (number of roads) [rows = " + rows + ", columns " + columns + "\n");
		str.append("4. Traffic pattern" + patternToString() + "\n");
		str.append("5. Car entry rate (seconds/car) [min = 1.0, max = 4.0]\n");
		str.append("6. Road segment length (meters) " + roadSegmentLength +"\n");
		str.append("7. Intersection length (meters) " + intersectionLength + "\n");
		str.append("8. Car length (meters) [min = " + minCarLength +", max  = " + maxCarLength +"]\n");
		str.append("9. Car maximum velocity (meters/second) [min = 1.0, max = " +  maxVelocity + "]\n");
		str.append("10. Car stop distance (meters) [min = 0.5, max = 5.0]\n");
		str.append("11. Car break distance (meters) " + breakDistance + "\n");
		str.append("12. Traffic light green time (seconds) " + greenLightTime + "\n");
		str.append("13. Traffic light yellow time (seconds) " + yellowLightTime + "\n");

		return str.toString();
	}

}
