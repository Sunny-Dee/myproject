package myproject.model;

import java.util.ArrayList;
import java.util.List;

import myproject.model.Road;

public abstract class LongRoad {
	private List<Road> roads = new ArrayList<Road>();
	public int rows;
	public int columns;
	Agent[][] intersections;
//	public boolean northSouthorEastWest;

	LongRoad(){};
	
	public void addRoad(Road r){
		roads.add(r);
	}

	
	public Road nextRoad(int index){
		return roads.get(index);
	}
	
	public boolean carCanGo(Road r){
		return r.canGo();
	}
	
	public String roadIndex(Road r){
		return "(" + r.i + "," + r.j + ")";
	}
	
	public int getRoadNum() {
		return roads.size();
	}
	
	public abstract boolean isDirectionNSWE();
}
