package myproject.model.agents;

import org.junit.Test;

public class LightTEST {
	private static final int GREENSTATE = 0; 
	double dimension = 10; 
	double greenDurationNS = 30;
	double yellowDurationNS = 15;
	Light light = new Light(GREENSTATE, dimension, greenDurationNS,  yellowDurationNS);
	
	@Test
	public void testSize(){
		
	}

}
