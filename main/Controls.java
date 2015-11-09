package myproject.main;


import myproject.model.Model2;
import myproject.ui.UI;
import myproject.ui.UIError;
import myproject.ui.UIForm;
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

	private UIForm getTrafficForm;
	private UIFormTest numberTest;
	private UIFormTest stringTest;

	private Model2 model;
	private UI ui;
	
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
		stringTest = input -> ! "".equals(input.trim());
		
		//Consider doing something like this for paramaters:

//		UIFormBuilder f = new UIFormBuilder();
//		f.add("Title", stringTest);
//		f.add("Year", yearTest);
//		f.add("Director", stringTest);
//		getTrafficForm = f.toUIForm("Enter Video");	

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
							() -> {});
					subMenu.add("Simulation time step", 
							() -> {});
					subMenu.add("Simulation runtime", 
							() -> {});
					subMenu.add("Grid size", 
							() -> {});
					subMenu.add("Set traffic pattern", 
							() -> {});
					subMenu.add("Set car entry rate", 
							() -> {});
					subMenu.add("Set road lengths", 
							() -> {});
					subMenu.add("Set intersection lengths", 
							() -> {});
					subMenu.add("Set car length", 
							() -> {});
					subMenu.add("Set max car velocity", 
							() -> {});
					subMenu.add("Set max car velocity", 
							() -> {});
					subMenu.add("Set car stop distance", 
							() -> {});
					subMenu.add("Set car break distance", 
							() -> {});
					subMenu.add("Set traffic light green times", 
							() -> {});
					subMenu.add("Set traffic light yellow times", 
							() -> {});
					subMenu.add("Reset simulation and return to main menu", 
							() -> {});
					subMenu.add("Return to main menu", 
							() -> {});
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
