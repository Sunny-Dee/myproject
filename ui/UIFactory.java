package myproject.ui;

/**
 * Creates either kind of UI depending on which method is called. 
 */

public class UIFactory {
	public UIFactory() {
	}

	static private UI popUI = new PopupUI();

	static private UI textUI = new TextUI();
	
	static public UI popUI() {
		return popUI;
	}
	
	static public UI textUI() {
		return textUI;
	}
}
