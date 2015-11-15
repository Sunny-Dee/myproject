package myproject.main;

import myproject.model.TrafficBuilder;
//import myproject.model.swing.SwingAnimatorBuilder;
import myproject.model.text.TextAnimatorBuilder;

public class MyMain2 {
	private MyMain2() {
	}

	public static void main(String[] args) {
		{

			TrafficBuilder tb = new TrafficBuilder(new TextAnimatorBuilder());
			tb.runModel();
		}


//		{
//
//			TrafficBuilder tb = new TrafficBuilder(new SwingAnimatorBuilder());
//			tb.runModel();
//		}

		System.exit(0);
	}
}
