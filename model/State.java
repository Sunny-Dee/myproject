package myproject.model;

public interface State {
	void getState();

}

class GreenNSRedEW implements State{

	public void getState() {
		System.out.println("Light state is GreenNS/RedEW."); 
		
	}
	
}

class YellowNSRedEW implements State{

	public void getState() {
		System.out.println("Light state is YellowNS/RedEW."); 
		
	}
	
}

class RedNSGreenEW implements State{

	public void getState() {
		System.out.println("Light state is RedNS/GreenEW."); 
		
	}
	
}

class RedNSYellowEW implements State{

	public void getState() {
		System.out.println("Light state is RedNS/YellowEW."); 
		
	}
	
}