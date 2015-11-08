package myproject.main;

import myproject.model.Model2;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.model.text.TextAnimatorBuilder;

public class MyMain2 {
	private MyMain2() {}
	public static void main(String[] args) {
		{
			Model2 m = new Model2(new TextAnimatorBuilder(), 0, 1);
			m.run(10);
			m.dispose();
		}
//		{
//			Model2 m = new Model2(new SwingAnimatorBuilder(), 0, 1);
//			m.run(10);
//			m.dispose();
//		}
//		{
//			Model2 m = new Model2(new TextAnimatorBuilder(), 1, 1);
//			m.run(10);
//			m.dispose();
//		}
		{
			Model2 m = new Model2(new SwingAnimatorBuilder(), 2, 2);
			m.run(1000);
			m.dispose();
		}
//		{
//			Model2 m = new Model2(new SwingAnimatorBuilder(), 2, 3);
//			m.run(500);
//			m.run(500);
//			m.dispose();
//		}
		System.exit(0);
	}
}
