package myproject.model;


public class Intersection implements Agent{
	private Light light;
	private double intersectionLength;
	


	public Intersection(Light light){
		this.light = light;
		intersectionLength = 10; // Hard coding this for now, change it later

	}
	
	public boolean canGo(){
		//light.setTempBoolean();
		return light.canGo();
			
	}
	
	public boolean isNull(){
		return false;
	};
	
	public double getDimension(){
		return intersectionLength;
	}
	
	public void run(double time){
		light.run(time);
		
//		if (time%40==0) {
//			
//			light.setTempBoolean();
//			
//		}
	}
	
}
