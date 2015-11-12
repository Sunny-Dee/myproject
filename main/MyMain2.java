package myproject.main;

import myproject.model.Agent;
import myproject.model.Intersection;
import myproject.model.Light;
import myproject.model.LongVerticalRoad;
import myproject.model.TrafficBuilder;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.model.text.TextAnimatorBuilder;

public class MyMain2 {
	private MyMain2() {}
	public static void main(String[] args) {
		{
////			Model2 m = new Model2(new TextAnimatorBuilder(), new TrafficBuilder(), 0, 1);
////			m.run(100);
////			m.dispose();
			TrafficBuilder tb = new TrafficBuilder(new TextAnimatorBuilder());
			tb.runModel();
		}
//		{
//			Model2 m = new Model2(new SwingAnimatorBuilder(), new TrafficBuilder(), 0, 1);
//			m.run(10);
//			m.dispose();
//		}
//		{
//			Model2 m = new Model2(new TextAnimatorBuilder(), new TrafficBuilder(), 1, 1);
//			m.run(10);
//			m.dispose();
//		}
		{
////			Model2 m = new Model2(new SwingAnimatorBuilder(), 2, 2);
////			m.run(1000);
////			m.dispose();
//			
			TrafficBuilder tb = new TrafficBuilder(new SwingAnimatorBuilder());
			tb.runModel();
		}
//		{
//			m.run(500);
//			m.run(500);
//			m.dispose();
//		}
//		{
//			Agent[][] intersections = new Agent[3][3];
//			for (int i=0; i<3; i++) {
//				for (int j=0; j<3; j++) {		
//					intersections[i][j] = new Intersection(new Light((int)( Math.random()*10)%3, 
//							50, 30));
//				}
//			}
//			LongVerticalRoad l = new LongVerticalRoad(2, 2, intersections, true);
//			l.createLongRoad();
//			System.out.println(l.nextRoad(0).getRoadID());
//			System.out.println(l.nextRoad(1).getRoadID());
//			System.out.println(l.nextRoad(2).getRoadID());
//			
//			l.reverseRoads();
//			System.out.println(l.nextRoad(0).getRoadID());
//			System.out.println(l.nextRoad(1).getRoadID());
//			System.out.println(l.nextRoad(2).getRoadID());
//			
//
//		}
		System.exit(0);
	}
}
