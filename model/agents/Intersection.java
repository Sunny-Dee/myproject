package myproject.model.agents;

import myproject.model.Model;

public class Intersection implements Agent {
	private Light light;
	private double intersectionLength;
	private Model model;

	public Intersection(Model model, Light light, double intersectionLength) {
		this.model = model;
		this.light = light;
		this.intersectionLength = intersectionLength; // Hard coding this for now, change it later
	}

	public java.awt.Color getColor() {
		return light.getColor();
	}

	public int getState() {
		return light.getState();
	}

	public boolean isNull() {
		return false;
	};

	public double getDimension() {
		return intersectionLength;
	}
	
	public double getRoadLength(){
		return model.roadLen();
	}

	public void run(double time) {
		light.run(time);

	}

}
