package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
				addRoad(new Road(i , j, true)); //change road length to user input		
		}
		for (Road r : roads){
			r.setIntersection(intersections[r.i][r.j]);
		}
		if (!eastWest)
			roads.get(columns).setIntersection(new NullIntersection());
	}
	
	
	public boolean isDirectionNSWE(){
		return eastWest;
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
		ListIterator<Road> ri = roads.listIterator();
		List<Road> tempList = new ArrayList<Road>();
		while (ri.hasPrevious()){
			tempList.add(ri.previous());
		}
		roads = tempList;
	}
	
	public List<Road> getRoads(){
		List<Road> result = new ArrayList<Road>();
		for (Road r : roads)
			result.add(r);
		return result;
	}

}
