package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import myproject.model.text.TextAnimatorBuilder;
import myproject.util.Animator;

public class Scenario extends Observable {
	private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;

	
	public Scenario(AnimatorBuilder builder, int rows, int columns) {
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
	
	public void dispose() {
		animator.dispose();
		disposed = true;
	}
	
	private void setup(AnimatorBuilder builder, int rows, int columns) {
		List<Road> roads = new ArrayList<Road>();
		Intersection[][] intersections = new Intersection[rows][columns];

		// Add Lights
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				intersections[i][j] = new Intersection(new Light());
				builder.addLight(intersections[i][j], i, j);
				agents.add(intersections[i][j]);
			}
		}

		// Add Horizontal Roads
		boolean eastToWest = false;
		for (int i=0; i<rows; i++) {
			for (int j=0; j<=columns; j++) {
				Road l = new Road(i, j);
				builder.addHorizontalRoad(l, i, j, eastToWest);
				roads.add(l);
			}
			eastToWest = !eastToWest;
		}

		// Add Vertical Roads
		boolean southToNorth = false;
		for (int j=0; j<columns; j++) {
			for (int i=0; i<=rows; i++) {
				Road l = new Road(i, j);
				builder.addVerticalRoad(l, i, j, southToNorth);
				roads.add(l);
			}
			southToNorth = !southToNorth;
		}

		// Add Cars
		for (Road l : roads) {
			Car car = new Car(l);
			agents.add(car);
			l.accept(car);
		}
	}
	
	public static void main(String [] args){
		Scenario m = new Scenario(new TextAnimatorBuilder(), 1, 1);
		
		System.out.println("Test");
		m.run(10);
		m.dispose();
		
		System.exit(0);
	}
}