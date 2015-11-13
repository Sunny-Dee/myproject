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
		Agent[][] intersections = new Agent[rows+1][columns+1];

		//Add Intersections
		for (int i=0; i<=rows; i++) {
			for (int j=0; j<=columns; j++) {
					
				intersections[i][j] = new Intersection(new Light((int)( Math.random()*10)%3, 
						t.intersectionLen(),t.greenLightTime(), t.yellowLightTime()));
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
			LongHorizontalRoad temproad = new LongHorizontalRoad(eastToWest);
			if (eastToWest){ //if true, pattern is alternating.
				for (int j=columns; j>=0; j--) {
					Road l = new Road(t.roadSegmentLen(), true);
					
					l.setIntersection(intersections[i][j]);
					if (j == 0) 
						l.setIntersection(new NullIntersection());
					
					builder.addHorizontalRoad(l, i, j, eastToWest);
					temproad.addRoad(l);

				}
				Car car = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				agents.add(car);
	
			} 
			
			else { //pattern is not alternating
				for (int j=0; j<=columns; j++) {
					Road l = new Road(t.roadSegmentLen(),true);
					
					l.setIntersection(intersections[i][j]);
					if (j == columns)
						l.setIntersection(new NullIntersection());
					
					builder.addHorizontalRoad(l, i, j, eastToWest);
					temproad.addRoad(l);
	
					}
				Car car = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				agents.add(car);
			}
			if (!simple) {eastToWest = !eastToWest;}
		}
		

		
		
		// Add Vertical Roads
		boolean southToNorth = false;
		for (int j=0; j<columns; j++) {
			LongVerticalRoad temproad2 = new LongVerticalRoad(southToNorth);
			if (southToNorth){
				for (int i=rows; i>=0; i--) {
					Road l = new Road(t.roadSegmentLen(),false);
					l.setIntersection(intersections[j][i]);
					if (i == 0)
						l.setIntersection(new NullIntersection());
					
					builder.addVerticalRoad(l, i, j, southToNorth);
					temproad2.addRoad(l);
				}
				Car car = new Car(temproad2, this, t.maxVel(), t.carLen(),t.breakDist(), t.stopDist());
				agents.add(car);
			}
			else {
				for (int i=0; i<=rows; i++) {
					Road l = new Road(t.roadSegmentLen(),false);
					l.setIntersection(intersections[j][i]);
					if (i == rows)
						l.setIntersection(new NullIntersection());
					
					builder.addVerticalRoad(l, i, j, southToNorth);
					temproad2.addRoad(l);
				}
				Car car = new Car(temproad2, this, t.maxVel(), t.carLen(),t.breakDist(), t.stopDist());
				agents.add(car);
			}
			if (!simple) {southToNorth = !southToNorth;}
		}
		
	}
	
	public void addAgent(Agent a){
		agents.add(a);
	}	
	
}
