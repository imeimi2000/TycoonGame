package hs.sshs.carp;

import java.awt.Graphics;

public class Paste extends ClickAble {
	private boolean selected;
	public Paste(int x, int y){
		super(x, y);
		size = GameManager.pasteSize;
	}

	public void select() {
		selected = true;
	}

	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("paste"), location.getX(), location.getY(), size.getX(), size.getY(), GameManager.getScreen());
		if (selected) {
			g.drawRect(location.getX(), location.getY(), size.getX(), size.getY());
			selected = false;
		}
	}
}
