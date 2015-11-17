package myproject.model.roads;

import java.util.ArrayList;
import java.util.List;

/**
 * A long road holds road segments and lights. Indicates the car what road next.
 */

public class LongRoad {
	private List<Road> roads;
	public boolean oppositeDirection;

	public LongRoad(boolean oppositeDirection) {
		this.oppositeDirection = oppositeDirection;
		roads = new ArrayList<Road>();
	};

	public void addRoad(Road r) {
		roads.add(r);
	}

	public Road nextRoad(int index) {
		return roads.get(index);
	}

	public int getRoadNum() {
		return roads.size();
	}

	public boolean oppositeDirection() {
		return oppositeDirection;
	}

}
