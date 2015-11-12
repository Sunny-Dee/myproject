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

//	public void createLongRoad(){
//		for (int i = 0; i<rows; i++){
//			for (int j = 0; j<=columns; j++)
//				addRoad(new Road(i , j, true)); //change road length to user input		
//		}
//		for (Road r : roads){
//			r.setIntersection(intersections[r.i][r.j]);
//		}
		
		//this is not accurate
//		if (!northSouthorEastWest)
//			roads.get(columns).setIntersection(new NullIntersection());
//	}
	
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
