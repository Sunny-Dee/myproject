package myproject.model;

public class NullIntersection implements Agent {
	public boolean isNull() {
		return true;
	};

	public boolean canGo() {
		return true;

	}

	public void run(double time) {

	}

	public double getDimension() {
		return 10;
	}

	public int getState() {
		return -1;
	}

}
