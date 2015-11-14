package myproject.model;

public class CarGenerator implements Agent {
	private LongRoad longRoad;
	private Model2 model;
	private double carVelocity;
	private double carLength;
	private double stopDistance;
	private double brakeDistance;

	CarGenerator(LongRoad longRoad, Model2 model, double carVelocity, double carLength, double brakeDistance,
			double stopDistance) {
		this.longRoad = longRoad;
		this.model = model;
		this.carVelocity = carVelocity;
		this.carLength = carLength;
		this.brakeDistance = brakeDistance;
		this.stopDistance = stopDistance;

	}

	@Override
	public void run(double time) {
		// TODO Auto-generated method stub
		if (time % 7 == 0) {
			Car car = new Car(longRoad, model, carVelocity, carLength, brakeDistance, stopDistance);
			model.addAgent(car);
		}
	}

	public boolean isNull() {
		return false;
	}

	@Override
	public double getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

}
