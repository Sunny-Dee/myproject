package myproject.model.agents;

/**
 * Null intersections signal a car it's time to kill itself since they are off
 * the grid.
 */

public class NullIntersection implements Agent {
	public boolean isNull() {
		return true;
	};

	public boolean canGo() {
		return true;

	}

	public void run(double time) {

	}

	public double getDimension() {
		return 10;
	}

	public int getState() {
		return -1;
	}

}
