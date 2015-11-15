package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import myproject.model.agents.Agent;
import myproject.model.agents.Car;
import myproject.model.agents.CarGenerator;
import myproject.model.agents.Intersection;
import myproject.model.agents.Light;
import myproject.model.agents.NullIntersection;
import myproject.model.roads.LongRoad;
import myproject.model.roads.Road;
import myproject.util.Animator;

public class Model extends Observable {
	private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time; //time step
	private TrafficBuilder t;
	private int rows;
	private int columns;
	private boolean simple;

	public Model(AnimatorBuilder builder, TrafficBuilder t) {
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
		for (int i = 0; i < duration; i++) {
			time++;
			// iterate through a copy because agents may change during
			// iteration...
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
	
	public void addAgent(Agent a) {
		agents.add(a);
	}

	public void removeAgent(Agent a) {
		agents.remove(a);
	}

	/**
	 * Construct the model, establishing correspondences with the visualizer.
	 */
	private void setup(AnimatorBuilder builder, int rows, int columns) {
		simple = t.pattern();
		Agent[][] intersections = new Agent[rows + 1][columns + 1];

		// Add Intersections
		for (int i = 0; i <= rows; i++) {
			for (int j = 0; j <= columns; j++) {

				intersections[i][j] = new Intersection(this, new Light((int) (Math.random() * 10) % 3, t.intersectionLen(),
						t.greenLightTime(), t.yellowLightTime()), t.intersectionLen());
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				builder.addLight(intersections[i][j], i, j);
				agents.add(intersections[i][j]);
			}
		}

		// Add Horizontal Roads
		boolean eastToWest = false;
		for (int i = 0; i < rows; i++) {
			LongRoad temproad = new LongRoad(eastToWest);
			if (eastToWest) { // if true, pattern is alternating.
				for (int j = columns; j >= 0; j--) {
					Road l = new Road(t.roadSegmentLen(), true);

					
					if (j == 0) {
						l.setIntersection(new NullIntersection());
					} else {
						l.setIntersection(intersections[i][j-1]);
					}


					builder.addHorizontalRoad(l, i, j, eastToWest);
					temproad.addRoad(l);

				}
//				Car cars = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				CarGenerator cars = new CarGenerator(temproad, this, t.maxVel(), t.carLen(), t.breakDist(),
						t.stopDist(), t.entryRate());
				
				agents.add(cars);

			}

			else { // pattern is not alternating
				for (int j = 0; j <= columns; j++) {
					Road l = new Road(t.roadSegmentLen(), true);

					l.setIntersection(intersections[i][j]);
					if (j == columns)
						l.setIntersection(new NullIntersection());

					builder.addHorizontalRoad(l, i, j, eastToWest);
					temproad.addRoad(l);

				}
				Car cars = new Car(temproad, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
//				CarGenerator cars = new CarGenerator(temproad, this, t.maxVel(), t.carLen(), t.breakDist(),
//						t.stopDist(), t.entryRate());
				
				agents.add(cars);
			}
			if (!simple) {
				eastToWest = !eastToWest;
			}
		}

		// Add Vertical Roads
		boolean southToNorth = false;
		for (int j = 0; j < columns; j++) {
			LongRoad temproad2 = new LongRoad(southToNorth);
			if (southToNorth) {
				for (int i = rows; i >= 0; i--) {
					Road l = new Road(t.roadSegmentLen(), false);
					
					if (i == 0) {
						l.setIntersection(new NullIntersection());
					} else {
						l.setIntersection(intersections[i-1][j]);
					}

					builder.addVerticalRoad(l, i, j, southToNorth);
					temproad2.addRoad(l);
				}
				Car cars = new Car(temproad2, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				
//				CarGenerator cars = new CarGenerator(temproad2, this, t.maxVel(), t.carLen(), t.breakDist(),
//						t.stopDist(), t.entryRate());
				agents.add(cars);
			} else {
				for (int i = 0; i <= rows; i++) {
					Road l = new Road(t.roadSegmentLen(), false);
					l.setIntersection(intersections[i][j]);
					if (i == rows)
						l.setIntersection(new NullIntersection());

					builder.addVerticalRoad(l, i, j, southToNorth);
					temproad2.addRoad(l);
				}
				Car cars = new Car(temproad2, this, t.maxVel(), t.carLen(), t.breakDist(), t.stopDist());
				
//				CarGenerator cars = new CarGenerator(temproad2, this, t.maxVel(), t.carLen(), t.breakDist(),
//						t.stopDist(), t.entryRate());
				agents.add(cars);
			}
			if (!simple) {
				southToNorth = !southToNorth;
			}
		}
		
	}
	
	public double roadLen ( ){
		return t.roadSegmentLen();
	}

}
