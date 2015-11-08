package myproject.model;

import java.util.ArrayList;
import java.util.List;

public class LongVerticalRoad implements LongRoad {
	private List<Road> roads = new ArrayList<Road>();
	public int rows;
	public int columns;
	Agent[][] intersections;
	public boolean northSouth;
	
	public LongVerticalRoad(int rows, int columns, Agent[][] intersections, boolean orientationNS){
		this.rows = rows;
		this.columns = columns;
		this.intersections = intersections; 
		northSouth = orientationNS;
	};
	
	public void addRoad(Road r){
		roads.add(r);
	}
	
	public void createLongRoad(){
		for (int i = 0; i<columns; i++){
			for (int j = 0; j<=rows; j++)
				addRoad(new Road(i , j, false)); //change road length to user input		
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
		return northSouth;
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
