package myproject.main;

import myproject.model.Car;
import myproject.model.Intersection;
import myproject.model.Light;
import myproject.model.LongHorizontalRoad;

public class MyMain {
	public static void main(String [] args){
		int rows = 1;
		int columns = 2;
		Intersection[][] intersections = new Intersection[rows+1][columns+1];
		for (int i=0; i<=rows; i++) {
			for (int j=0; j<=columns; j++) 
				intersections[i][j] = new Intersection(new Light(1));
		}
		
		LongHorizontalRoad bigRoad = new LongHorizontalRoad(rows, columns, intersections, false);
		bigRoad.createLongRoad();


		Car car = new Car(bigRoad);
		
		while(!car.carSunk())
			car.drive();
//		car.drive();
//		car.drive();


		
		
	}	
	

	
}
