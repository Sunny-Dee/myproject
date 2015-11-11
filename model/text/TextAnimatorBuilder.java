package myproject.model.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import myproject.model.Agent;
import myproject.model.AnimatorBuilder;
import myproject.model.Car;
import myproject.model.Road;
import myproject.util.Animator;

/**
 * A class for building Animators.
 */
public class TextAnimatorBuilder implements AnimatorBuilder {
	TextAnimator animator;
	public TextAnimatorBuilder() {
		this.animator = new TextAnimator();
	}	
	public Animator getAnimator() {
		if (animator == null) { throw new IllegalStateException(); }
		Animator returnValue = animator;
		animator = null;
		return returnValue;
	}
	public void addLight(Agent d, int i, int j) {
		animator.addLight(d,i,j);
	}
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		animator.addRoad(l,i,j);
	}
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		animator.addRoad(l,i,j);
	}
	
	public void clear(){
		animator = new TextAnimator();
	}


	/** Class for drawing the Model. */
	private static class TextAnimator implements Animator {

		/** Triple of a model element <code>x</code> and coordinates. */
		private static class Element<T> {
			T x;
			int i;
			int j;
			Element(T x, int i, int j) {
				this.x = x;
				this.i = i;
				this.j = j;
			}
		}

		private List<Element<Road>> roadElements;
		private List<Element<Agent>> lightElements;
		TextAnimator() {
			roadElements = new ArrayList<Element<Road>>();
			lightElements = new ArrayList<Element<Agent>>();
		}
		void addLight(Agent x, int i, int j) {
			lightElements.add(new Element<Agent>(x,i,j));
		}
		void addRoad(Road x, int i, int j) {
			roadElements.add(new Element<Road>(x,i,j));
		}

		public void dispose() { }

		public void update(Observable o, Object arg) {
			for (Element<Agent> e : lightElements) {
				System.out.print("Light at (" + e.i + "," + e.j + "): ");
				if (e.x.getState() == 0) {
					System.out.println("Green");
				} else if (e.x.getState() == 1) {
					System.out.println("Yellow");
				} else
					System.out.println("Green");
			}
			for (Element<Road> e : roadElements) {
				for (Car d : e.x.getCars()) {
					System.out.print("Road at (" + e.i + "," + e.j + "): ");
					System.out.println("Car at " + d.getPosition());
				}
			}
		}
	}
}
