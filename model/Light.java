package myproject.model;

/**
 * A light has a boolean state.
 */
public class Light implements Agent {
	 public boolean isNull = false;	
	 private double greenDurationNS;  // Duration of the North/South green phase (in seconds)
	 private double yellowDurationNS; // Duration of the North/South yellow phase (in seconds)
	 private double greenDurationEW;  // Duration of the East/West green phase (in seconds)
	 private double yellowDurationEW; // Duration of the East/West yellow phase (in seconds)
	 private boolean state;
	 private State lightState;
	
	public Light() { 
		state = false;
		lightState = new GreenNSRedEW();
		greenDurationNS  = 55.0;
		yellowDurationNS = 5.0;
		greenDurationEW  = 25.0;
		yellowDurationEW = 5.0;
	} // Created only by this package. Default values. I changed it to public

	Light(double greenDurationNS, double yellowDurationNS, 
			double greenDurationEW, double yellowDurationEW) { 
		state = false;
		lightState = new GreenNSRedEW();
		this.greenDurationNS  = greenDurationNS;
		this.yellowDurationNS = yellowDurationNS;
		this.greenDurationEW  = greenDurationEW;
		this.yellowDurationEW = yellowDurationEW;
	} // Created only by this package

	public boolean getState() {
		return state;
	}
	
	public boolean isNull(){
		return false;
	};
	
	public void setLightState(State newState){
		lightState = newState;
	}
	
	//THE FOLLOWING TWO METHODS ARE JUST SO I CAN KEEP PLAYING WITH THIS!!!!!
	//MUST FIX LATER
	public void setTempBoolean(){
		state = !state; 
	}
	
	public boolean canGo(){
		return state; 
	}
	
	public void run(double time) {
		if (time%70==0) {
			state = !state;
		}
		
		if (time%greenDurationNS==0){
			setLightState(new YellowNSRedEW());
			lightState.getState();
		}
		
		if (time% (greenDurationNS + yellowDurationNS) == 0){
			setLightState(new RedNSGreenEW());
			lightState.getState();
			
		}
		
		if (time% (greenDurationNS + yellowDurationNS 
				 + greenDurationEW) == 0){
			setLightState(new RedNSYellowEW());
			lightState.getState();
		}
		
		if (time% (greenDurationNS + yellowDurationNS 
				 + greenDurationEW + yellowDurationEW) == 0){
			setLightState(new GreenNSRedEW());
			lightState.getState();
		}
	}

	//Temporarily hard coding this but will change it later. 
	public double getDimension() {
		return 10;
	}
	
}

