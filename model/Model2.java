package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import myproject.util.Animator;

public class Model2 extends Observable{
	private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;
	private TrafficBuilder t;
	private int rows;
	private int columns;
	private boolean simple;

	public Model2(AnimatorBuilder builder, TrafficBuilder t) {
		this.t = t;
		rows = t.rows();
		columns = t.columns();
		
		if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
			throw new IllegalArgumentException();
		}
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		this.agents = new ArrayList<Agent>();
		setup(builder, rows, columns);
		this.animator = builder.getAnimator();
		super.addObserver(animator);
		simple = t.pattern(); 
	}
	
	public void run(double duration) {
		if (disposed)
			throw new IllegalStateException();
		for (int i=0; i<duration; i++) {
			time++;
			// iterate through a copy because agents may change during iteration...
			for (Agent a : agents.toArray(new Agent[0])) {
				a.run(time);
			}
			super.setChanged();
			super.notifyObservers();
		}
	}

	/**
	 * Throw away this model.
	 */
	public void dispose() {
		animator.dispose();
		disposed = true;
	}
	protected void removeAgent(Agent a) {
		agents.remove(a);
	}

	/**
	 * Construct the model, establishing correspondences with the visualizer.
	 */
	private void setup(AnimatorBuilder builder, int rows, int columns) {
		simple = t.pattern();
		List<Road> roads = new ArrayList<Road>();
		Agent[][] intersections = new Agent[rows+1][columns+1];

		//Add Intersections
		for (int i=0; i<=rows; i++) {
			for (int j=0; j<=columns; j++) {
					
				intersections[i][j] = new Intersection(new Light((int)( Math.random()*10)%3, 
						t.greenLightTime(), t.yellowLightTime()));
//				if ((i == rows) || (j == columns))
//					intersections[i][j] = new NullIntersection();
//				builder.addLight(intersections[i][j], i, j);
//				agents.add(intersections[i][j]);
			}
		}
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				builder.addLight(intersections[i][j], i, j);
				agents.add(intersections[i][j]);
			}
		}
		
		
		
		// Add Horizontal Roads
		boolean eastToWest = false;
		for (int i=0; i<rows; i++) {
			LongHorizontalRoad temproad = new LongHorizontalRoad(rows, columns, intersections, eastToWest);
			if (eastToWest){ //if true, pattern is alternating.
				for (int j=columns; j>=0; j--) {
					Road l = new Road(t.roadSegmentLen(), i, j, true);
					
					l.setIntersection(intersections[i][j]);
					
					builder.addHorizontalRoad(l, i, j, eastToWest);
					roads.add(l);
					temproad.addRoad(l);

				}
				Car car = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				agents.add(car);
	
			} 
			
			else { //pattern is alternating
				for (int j=0; j<=columns; j++) {
					Road l = new Road(t.roadSegmentLen(), i, j, true);
					
					l.setIntersection(intersections[i][j]);
					
					builder.addHorizontalRoad(l, i, j, eastToWest);
					roads.add(l);
					temproad.addRoad(l);
	
					}
				Car car = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				agents.add(car);
			}
			if (!simple) {eastToWest = !eastToWest;}
		}
		
		
		

//		// Add Horizontal Roads
//		boolean eastToWest = false;
//		for (int i=0; i<rows; i++) {
//			LongHorizontalRoad temproad = new LongHorizontalRoad(rows, columns, intersections, eastToWest);
//			
//			for (int j=0; j<=columns; j++) {
//				Road l = new Road(t.roadSegmentLen(), i, j, true);
//				
//				l.setIntersection(intersections[l.i][l.j]);
//				
//				builder.addHorizontalRoad(l, i, j, eastToWest);
//				roads.add(l);
//				temproad.addRoad(l);
//
//			}
//			Car car = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
//			agents.add(car);
//			
//			if (!simple) {eastToWest = !eastToWest;}
//		}
		
		
		
		// Add Vertical Roads
		boolean southToNorth = false;
		for (int j=0; j<columns; j++) {
			LongVerticalRoad temproad2 = new LongVerticalRoad(rows, columns, intersections, southToNorth);
			if (southToNorth){
				for (int i=rows; i>=0; i--) {
					Road l = new Road(t.roadSegmentLen(), j, i, false);
					
					l.setIntersection(intersections[j][i]);
					
					builder.addVerticalRoad(l, i, j, southToNorth);

					temproad2.addRoad(l);
				}
				Car car = new Car(temproad2, this, t.maxVel(), t.carLen(),t.breakDist(), t.stopDist());
				agents.add(car);
			}
			else {
				for (int i=0; i<=rows; i++) {
					Road l = new Road(t.roadSegmentLen(), j, i, false);
					
					l.setIntersection(intersections[j][i]);
					
					builder.addVerticalRoad(l, i, j, southToNorth);
					roads.add(l);
					temproad2.addRoad(l);
				}
				Car car = new Car(temproad2, this, t.maxVel(), t.carLen(),t.breakDist(), t.stopDist());
				agents.add(car);
			}
			if (!simple) {southToNorth = !southToNorth;}
		}
		
		
		

//		// Add Vertical Roads
//		boolean southToNorth = false;
//		for (int j=0; j<columns; j++) {
//			LongVerticalRoad temproad2 = new LongVerticalRoad(rows, columns, intersections, southToNorth);
//			for (int i=0; i<=rows; i++) {
//				Road l = new Road(t.roadSegmentLen(), j, i, false);
//				
//				l.setIntersection(intersections[l.j][l.i]);
//				
//				builder.addVerticalRoad(l, i, j, southToNorth);
//				roads.add(l);
//				temproad2.addRoad(l);
//			}
//			Car car = new Car(temproad2, this, t.maxVel(), t.carLen(),t.breakDist(), t.stopDist());
//			agents.add(car);
//			
////			southToNorth = !southToNorth;
//			if (!simple) {southToNorth = !southToNorth;}
//		}

		
	}
	
	public void addAgent(Agent a){
		agents.add(a);
	}	
	
}
