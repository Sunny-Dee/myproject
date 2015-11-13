package myproject.model;

import java.util.ArrayList;
import java.util.List;
//import java.util.ListIterator;

public class LongHorizontalRoad extends LongRoad {
	private List<Road> roads = new ArrayList<Road>();
	public boolean eastWest;

	
	public LongHorizontalRoad(boolean orientationEW){
		eastWest = orientationEW;
	};
	
	
	public boolean isDirectionNSWE(){
		return eastWest;
	}
	
	
//	public Road nextRoad(){
//		return roads.iterator().next();
//	}
	
	
//	public void reverseRoads(){
//		ListIterator<Road> ri = roads.listIterator();
//		List<Road> tempList = new ArrayList<Road>();
//		while (ri.hasPrevious()){
//			tempList.add(ri.previous());
//		}
//		roads = tempList;
//	}
	
	public List<Road> getRoads(){
		List<Road> result = new ArrayList<Road>();
		for (Road r : roads)
			result.add(r);
		return result;
	}

}
