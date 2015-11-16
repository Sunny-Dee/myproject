package myproject.main;

import myproject.model.AnimatorBuilder;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.model.text.TextAnimatorBuilder;
import myproject.ui.UI;
import myproject.ui.UIFactory;

public class Main {
	public static void main(String[] args) {
		UIFactory factory = new UIFactory();
		UI ui = null;
		AnimatorBuilder builder = null;

		if (Math.random() <= 0.9) {
			ui = UIFactory.createUI("popup");
			builder = new SwingAnimatorBuilder();	
		} else {
			ui = UIFactory.createUI("text");
			builder = new TextAnimatorBuilder();
		}
	
		Controls control = new Controls(builder, ui);
		control.run();
	}
}
