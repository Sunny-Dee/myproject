package myproject.model;

public class CarGenerator implements Agent {
	private LongRoad longRoad;
	private Model2 model;
	
	CarGenerator (LongRoad longRoad, Model2 model){
		this.longRoad = longRoad;
		this.model = model;
	}
	@Override
	public void run(double time) {
		// TODO Auto-generated method stub
		if (time % 7 == 0){
			Car car = new Car(longRoad, model);
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
