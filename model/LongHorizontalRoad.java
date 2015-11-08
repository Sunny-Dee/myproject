package myproject.model;

import java.util.ArrayList;
import java.util.List;

public class LongHorizontalRoad implements LongRoad {
	private List<Road> roads = new ArrayList<Road>();
	public int rows;
	public int columns;
	Agent[][] intersections;
	public boolean eastWest;

	
	public LongHorizontalRoad(int rows, int columns, Agent[][] intersections, boolean orientationEW){
		this.rows = rows;
		this.columns = columns;
		this.intersections = intersections; 
		eastWest = orientationEW;
	};
	
	public void addRoad(Road r){
		roads.add(r);
	}
	
	public void createLongRoad(){
		for (int i = 0; i<rows; i++){
			for (int j = 0; j<=columns; j++)
				addRoad(new Road(i , j)); //change road length to user input		
		}
		for (Road r : roads){
			r.setIntersection(intersections[r.i][r.j]);
		}
		roads.get(columns).setIntersection(new NullIntersection());
	}
	
//	public Road nextRoad(){
//		return roads.iterator().next();
//
//	}
	
	public boolean isDirectionNSWE(){
		return eastWest;
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

	public int getRoadSize() {
		return roads.size();
	}

}
