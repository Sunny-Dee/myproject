package myproject.main;

import myproject.model.AnimatorBuilder;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.model.text.TextAnimatorBuilder;
import myproject.ui.UI;

public class Main {
	public static void main(String[] args) {
		UI ui = null;
		AnimatorBuilder builder = null;

		if (args.length > 0) {
			if ("GUI".equalsIgnoreCase(args[0])) {
				ui = new myproject.ui.PopupUI();
			} else if ("TEXT".equalsIgnoreCase(args[0])) {
				ui = new myproject.ui.TextUI();
			} else {
				System.out.println("Argument must be GUI or TEXT");
				System.exit(1);
			}
		} else {
			if (Math.random() <= 0.5) {
				ui = new myproject.ui.TextUI();
				builder = new TextAnimatorBuilder();
			} else {
				ui = new myproject.ui.PopupUI();
				builder = new SwingAnimatorBuilder();
			}
		}
		Controls control = new Controls(builder, ui);
		control.run();
	}
}
