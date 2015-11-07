package myproject.model;

public class NullIntersection implements Agent{
	public boolean isNull(){
		return true;
	};
	public boolean canGo(){
		return false;
			
	}
	
	public void run(double time) {
		
		
	}

	@Override
	public double getDimension() {
		return 10;
	}

}
