package myproject.ui;

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
