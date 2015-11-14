package myproject.model;

import java.awt.Color;

/**
 * A light has a boolean state.
 */
public class Light implements Agent {
	public boolean isNull = false;
	private double greenDurationNS; // Duration of the North/South green phase
									// (in seconds)
	private double yellowDurationNS; // Duration of the North/South yellow phase
										// (in seconds)
	private double greenDurationEW; // Duration of the East/West green phase (in
									// seconds)
	private double yellowDurationEW; // Duration of the East/West yellow phase
										// (in seconds)
	private java.awt.Color color;
	private int state;
	private double duration;
	private double dimension;

	public Light(int state, double dimension, double greenDurationNS, double yellowDurationNS) {
		this.state = state % 3;
		this.greenDurationNS = greenDurationNS;
		this.yellowDurationNS = yellowDurationNS;
		this.dimension = dimension;

		greenDurationEW = 50.0;
		yellowDurationEW = 30.0;

		setColor();
	} // Created only by this package. Default values. I changed it to public

	// public boolean getState() {
	// return tempstate;
	// }

	public int getState() {
		return state;
	}

	public boolean isNull() {
		return false;
	};

	public java.awt.Color getColor() {
		return color;
	}

	private void setColor() {
		if (state == 0) {
			color = Color.GREEN;
			duration = greenDurationNS;
		} else if (state == 1) {
			color = Color.YELLOW;
			duration = yellowDurationNS;
		} else {
			color = Color.RED;
			duration = greenDurationEW + yellowDurationEW;
		}
	}

	public void run(double time) {

		duration = duration - 1;
		if (duration == 0) {
			state = (state + 1) % 3;
			setColor();
		}

	}

	// Temporarily hard coding this but will change it later.
	public double getDimension() {
		return dimension;
	}

}
