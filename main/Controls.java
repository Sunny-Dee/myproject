	package myproject.main;

import myproject.model.AnimatorBuilder;
import myproject.model.TrafficBuilder;
import myproject.ui.UI;
import myproject.ui.UIError;
//import myproject.ui.UIForm;
import myproject.ui.UIFormBuilder;
import myproject.ui.UIFormTest;
import myproject.ui.UIMenu;
import myproject.ui.UIMenuBuilder;

public class Controls {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int PARAMS = 3;
	private static final int NUMSTATES = 16;
	private UIMenu[] menus;
	private int state;

	private UIFormTest numberTest;
	private UIFormTest doubleTest;
	private UIFormTest stringTest;

	private UI ui;
	private TrafficBuilder trafficBuilder;
	private AnimatorBuilder builder;

	Controls(AnimatorBuilder builder, UI ui) {
		this.ui = ui;
		this.builder = builder;
		trafficBuilder = new TrafficBuilder(builder);

		menus = new UIMenu[NUMSTATES];
		state = START;
		addSTART(START);
		addSimParams(PARAMS);
		addEXIT(EXIT);

		numberTest = input -> {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};

		doubleTest = input -> {
			try {
				Double.parseDouble(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};

		stringTest = input -> !"".equals(input.trim());
	}

	void run() {
		try {
			while (state != EXITED) {
				ui.processMenu(menus[state]);
			}
		} catch (UIError e) {
			ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", () -> ui.displayError("doh!"));

		m.add("Run simulation", () -> {
			trafficBuilder.runModel();
		});
		m.add("Change simulation parameters", () -> state = PARAMS);
		m.add("Exit", () -> state = EXIT);
		menus[stateNum] = m.toUIMenu("Traffic Simulator");
	}

	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", () -> {
		});
		m.add("Yes", () -> state = EXITED);
		m.add("No", () -> state = START);

		menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}

	private void addSimParams(int stateNum) {
		UIMenuBuilder s = new UIMenuBuilder();

		s.add("Default", () -> {
		});

		s.add("Show current values", () -> {
			ui.displayMessage(trafficBuilder.toString());
		});
		s.add("Simulation time step", () -> {
			UIFormBuilder f = new UIFormBuilder();
			f.add("Enter new simulation time step", numberTest);
			String[] result1 = ui.processForm(f.toUIForm("Simulation time step"));
			trafficBuilder.setTimeStep(Integer.parseInt(result1[0]));
		});
		s.add("Simulation runtime", () -> {
			UIFormBuilder f = new UIFormBuilder();
			f.add("Enter new simulation runtime", numberTest);
			String[] result = ui.processForm(f.toUIForm("Simulation runtime"));
			trafficBuilder.settime(Integer.parseInt(result[0]));
		});
		s.add("Grid size", () -> {
			UIFormBuilder f = new UIFormBuilder();
			f.add("Enter number of rows, default is 2", numberTest);
			f.add("Enter number of columns, default is 3", numberTest);
			String[] result = ui.processForm(f.toUIForm("Grid"));
			trafficBuilder.setGrid(Integer.parseInt(result[0]), Integer.parseInt(result[1]));

		});
		s.add("Set traffic pattern", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Enter 1 for simple pattern, 2 for alternating", numberTest);
				String[] result = ui.processForm(f.toUIForm("Pattern"));
				x = trafficBuilder.setPattern(Integer.parseInt(result[0]));
			} while (!x);
		});
		s.add("Set car entry rate", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Set Car entry rate (seconds/car) [min = 1.0, max = 4.0]", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Entry rate"));
				x = trafficBuilder.setEntryRate(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Set road lengths", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Road segment length (meters) default [min = 100.0, max = 200.0]", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Road length"));
				x = trafficBuilder.setRoadSegmentLength(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Set intersection lengths", () -> {
			UIFormBuilder f = new UIFormBuilder();
			f.add("Intersection length (meters) [min = 10.0, max  = 15.0]", doubleTest);
			String[] result = ui.processForm(f.toUIForm("Intersection length"));
			trafficBuilder.setIntersectionLength(Double.parseDouble(result[0]));
		});
		s.add("Set car length", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Set minimum car lenth" , doubleTest);
				f.add("Set maximum car length", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Car length"));
				x = trafficBuilder.setCarLength(Double.parseDouble(result[0]), 
						Double.parseDouble(result[1]));
			} while (!x);
		});
		s.add("Set max car velocity", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Set car maximum velocity default (meters/second) [min = 1.0, max = 6.0]  ", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Car velocity"));
				x = trafficBuilder.setMaxVelocity(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Set car stop distance", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Car stop distance (meters) [min = 0.5, max = 5.0]", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Stop distance"));
				x = trafficBuilder.setStopDistance(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Set car break distance", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Car break distance (meters) [min = 9.0, max = 10.0]", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Stop distance"));
				x = trafficBuilder.setBreakDistance(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Set traffic light green times", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Traffic light green time (seconds) [min = 30.0, max = 180.0]", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Stop distance"));
				x = trafficBuilder.setGreenLight(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Set traffic light yellow times", () -> {
			boolean x;
			do {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Set yellow traffic light time (seconds) [min = 32.0, max = 40.0]", doubleTest);
				String[] result = ui.processForm(f.toUIForm("Stop distance"));
				x = trafficBuilder.setGreenLight(Double.parseDouble(result[0]));
			} while (!x);
		});
		s.add("Reset simulation and return to main menu", () -> {
			builder.clear();
			trafficBuilder = new TrafficBuilder(builder);
			state = START;
		});
		s.add("Return to main menu", () -> {
			state = START;
		});
		menus[stateNum] = s.toUIMenu("Change Parameters");

	}

}
