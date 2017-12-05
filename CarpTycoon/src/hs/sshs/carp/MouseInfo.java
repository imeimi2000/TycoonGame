package hs.sshs.carp;

public class MouseInfo {
	private boolean clicked;
	private Point location;
	
	MouseInfo() {
		clicked = false;
		location = new Point();
	}
	
	public void clickLocation(int x, int y) {
		clicked = true;
		location.setX(x);
		location.setY(y);
	}
	public void init() {
		clicked = false;
	}
	public boolean mouseClicked() { return clicked; }
	public Point clickLocation() { return location; }
}
