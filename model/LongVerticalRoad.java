package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LongVerticalRoad extends LongRoad {
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
	
	
	public boolean isDirectionNSWE(){
		return northSouth;
	}
	
	
//	public Road nextRoad(){
//		return roads.iterator().next();
//	}
	
	//I believe this generates duplicates
	public void reverseRoads(){
		ListIterator<Road> ri = roads.listIterator(roads.size());
		List<Road> tempList = new ArrayList<Road>();
		while (ri.hasPrevious()){
			tempList.add(ri.previous());
			
		}
		roads = tempList;
	}

}
