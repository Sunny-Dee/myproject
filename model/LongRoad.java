package myproject.model;

import myproject.model.Road;

public interface LongRoad {

	public void addRoad(Road r);

	public void createLongRoad();
	
	public Road nextRoad(int index);
	
	public boolean carCanGo(Road r);
	
	public String roadIndex(Road r);
	
	public int getRoadSize();
	
	public boolean isDirectionNSWE();
}
