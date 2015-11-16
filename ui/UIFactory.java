package myproject.ui;

/**
 * Creates either kind of UI depending on which method is called. 
 */

public class UIFactory {
	public UIFactory() {
	}
	
	static public UI createUI(String uiType) {
		if (uiType == null)
			return null;
		else if (uiType.equalsIgnoreCase("popup"))
			return new PopupUI();
		else if (uiType.equalsIgnoreCase("text"))
			return new TextUI();
		return null;
	}
	
}
