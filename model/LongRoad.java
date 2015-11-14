package myproject.model;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

import myproject.model.Road;

public abstract class LongRoad {
	private List<Road> roads = new ArrayList<Road>();
	Agent[][] intersections;

	LongRoad() {
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

	public abstract boolean isDirectionNSWE();
}
