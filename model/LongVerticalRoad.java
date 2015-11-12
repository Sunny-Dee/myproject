package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
	
	
	public boolean isDirectionNSWE(){
		return northSouth;
	}
	
	public Road nextRoad(int index){
		return roads.get(index);

	}
	
//	public Road nextRoad(){
//		return roads.iterator().next();
//	}
	
	public boolean carCanGo(Road r){
		return r.canGo();
	}
	
	public String roadIndex(Road r){
		return "(" + r.i + "," + r.j + ")";
	}
	
	public int getRoadNum() {
		return roads.size();
	}
	
	public void reverseRoads(){
		ListIterator<Road> ri = roads.listIterator(roads.size());
		List<Road> tempList = new ArrayList<Road>();
		while (ri.hasPrevious()){
			tempList.add(ri.previous());
			
		}
		roads = tempList;
	}

}
