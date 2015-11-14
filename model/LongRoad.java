package myproject.model;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

import myproject.model.Road;

public class LongRoad {
	private List<Road> roads = new ArrayList<Road>();
	Agent[][] intersections;
	public boolean oppositeDirection;

	LongRoad(boolean oppositeDirection) {
		this.oppositeDirection = oppositeDirection;
	};

	public void addRoad(Road r) {
		roads.add(r);
	}

	public Road nextRoad(int index) {
		return roads.get(index);
	}

	// public Road nextRoad(){
	// Iterator<Road> roadIter = roads.iterator();
	// return roadIter.next();
	// }

	public boolean carCanGo(Road r) {
		return r.canGo();
	}

	public int getRoadNum() {
		return roads.size();
	}

	public boolean oppositeDirection(){
		return oppositeDirection;
	}
		
}
