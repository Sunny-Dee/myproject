package myproject.main;


import myproject.model.Model2;
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
	private static final int NUMSTATES = 16;
	private UIMenu[] menus;
	private int state;

	private UIFormTest numberTest;
	private UIFormTest doubleTest;
	private UIFormTest stringTest;

	private Model2 model;
	private UI ui;
	private TrafficBuilder trafficBuilder = new TrafficBuilder();
	
	Controls(Model2 model, UI ui) {
		this.model = model;
		this.ui = ui;

		menus = new UIMenu[NUMSTATES];
		state = START;
		addSTART(START);
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
			} catch (NumberFormatException e){
				return false;
			}
		};
		
		stringTest = input -> ! "".equals(input.trim());
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

		m.add("Default",
				() -> ui.displayError("doh!"));
		
		m.add("Run simulation",
				() -> {
						model.run(1000);
				});
		m.add("Change simulation parameters",
				() -> {
					UIMenuBuilder subMenu = new UIMenuBuilder();
					subMenu.add("Show current values", 
							() -> {
								trafficBuilder.toString();
							}); 
					subMenu.add("Simulation time step", 
							() -> {
								UIFormBuilder f = new UIFormBuilder();
								f.add("Current Simulation time step \nEnter new simulation time step", numberTest);
								String[] result1 = ui.processForm(f.toUIForm("Simulation time step"));
								trafficBuilder.setTimeStep(Integer.parseInt(result1[0]));		
							});
					subMenu.add("Simulation runtime", 
							() -> {
								UIFormBuilder f = new UIFormBuilder();
								f.add("Enter new simulation runtime", numberTest);
								String[] result = ui.processForm(f.toUIForm("Simulation runtime"));
								trafficBuilder.settime(Integer.parseInt(result[0]));	
							});
					subMenu.add("Grid size", 
							() -> {
								UIFormBuilder f = new UIFormBuilder();
								f.add("Enter number of rows, default is 2", numberTest);
								f.add("Enter number of columns, default is 3", numberTest);
								String[] result = ui.processForm(f.toUIForm("Grid"));
								trafficBuilder.setGrid(Integer.parseInt(result[0]), Integer.parseInt(result[1]));
							
							});
					subMenu.add("Set traffic pattern", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Enter 1 for simple pattern, 2 for alternating", numberTest);
									String[] result = ui.processForm(f.toUIForm("Pattern"));
									x = trafficBuilder.setPattern(Integer.parseInt(result[0]));
								} while (!x);
							});
					subMenu.add("Set car entry rate", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Car entry rate (seconds/car) [min = 1.0, max = 2.5]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Entry rate"));
									x = trafficBuilder.setEntryRate(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set road lengths", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Road segment length (meters) [min = 10.0, max = 15.0]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Road length"));
									x = trafficBuilder.setRoadSegmentLength(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set intersection lengths", 
							() -> {
								UIFormBuilder f = new UIFormBuilder();
								f.add("Intersection length (meters) [min = 10.0, max  = 15.0]", doubleTest);
								String[] result = ui.processForm(f.toUIForm("Intersection length"));
								trafficBuilder.setIntersectionLength(Double.parseDouble(result[0]));
							});
					subMenu.add("Set car length", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Car length (meters) [min = 10.0, max  = 15.0]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Car length"));
									x = trafficBuilder.setCarLength(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set max car velocity", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Car maximum velocity (meters/second) [min = 1.0, max = 3.0]  ", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Car velocity"));
									x = trafficBuilder.setMaxVelocity(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set car stop distance", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Car stop distance (meters) [min = 0.5, max = 5.0]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Stop distance"));
									x = trafficBuilder.setStopDistance(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set car break distance", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Car break distance (meters) [min = 9.0, max = 10.0]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Stop distance"));
									x = trafficBuilder.setBreakDistance(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set traffic light green times", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Traffic light green time (seconds) [min = 30.0, max = 180.0]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Stop distance"));
									x = trafficBuilder.setGreenLight(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Set traffic light yellow times", 
							() -> {
								boolean x;
								do {
									UIFormBuilder f = new UIFormBuilder();
									f.add("Traffic light yellow time (seconds) [min = 32.0, max = 40.0]", doubleTest);
									String[] result = ui.processForm(f.toUIForm("Stop distance"));
									x = trafficBuilder.setGreenLight(Double.parseDouble(result[0]));
								} while (!x);
							});
					subMenu.add("Reset simulation and return to main menu", 
							() -> {trafficBuilder = new TrafficBuilder(); }); 
					subMenu.add("Return to main menu", 
							() -> { state = START; }); 
					menus[NUMSTATES] = subMenu.toUIMenu("Change Parameters");

				});
		m.add("Exit",
				() -> state = EXIT);
		menus[stateNum] = m.toUIMenu("Traffic Simulator");
	}
	
	
	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", () -> {});
		m.add("Yes",
				() -> state = EXITED);
		m.add("No",
				() -> state = START);

		menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
	
//	  1. Show current values
//	  2. Simulation time step
//	  3. Simulation run time
//	  4. Grid size
//	  5. Traffic pattern
//	  6. Car entry rate
//	  7. Road segment length
//	  8. Intersection length
//	  9. Car length
//	 10. Car maximum velocity
//	 11. Car stop distance
//	 12. Car brake distance
//	 13. Traffic light green time
//	 14. Traffic light yellow time
//	 15. Reset simulation and return to the main menu
}
