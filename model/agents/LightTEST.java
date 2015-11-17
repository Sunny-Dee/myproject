package myproject.model.agents;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class LightTEST {
	private static final int GREENSTATE = 0;
	private static final int YELLOWSTATE = 1;
	private static final int REDSTATE = 2;
	double dimension = 10;


	@Test
	public void testStates() {
		Light light1 = new Light(GREENSTATE, dimension, 20, 10);
		Light light2 = new Light(YELLOWSTATE, dimension, 20, 10);
		
		assertEquals(GREENSTATE, light1.getState());
		assertEquals(YELLOWSTATE, light2.getState());
		
		for (int i = 0; i <= 21 ; i++)
			light1.run(1);
		assertEquals(YELLOWSTATE, light1.getState());
		
		for (int i = 0; i <= 10 ; i++)
			light1.run(1);
		assertEquals(REDSTATE, light1.getState());
		
		for (int i = 0; i <= 80 ; i++)
			light1.run(1);
		assertEquals(GREENSTATE, light1.getState());
	}
	
	@Test
	public void testColors(){
		Light light1 = new Light(GREENSTATE, dimension, 20, 10);
		assertEquals(Color.GREEN, light1.getColor());
		
		for (int i = 0; i <= 21 ; i++)
			light1.run(1);
		assertEquals(Color.YELLOW, light1.getColor());
		
		for (int i = 0; i <= 10 ; i++)
			light1.run(1);
		assertEquals(Color.RED, light1.getColor());
	}

}
