package hs.sshs.carp;

import java.awt.Graphics;

public class MaterialSelector extends ClickAble {
	private final int type;
	private boolean selected;
	
	MaterialSelector(int x, int y, int t) {
		super(x, y);
		type = t;
		size = GameManager.sauceSize;
	}
	
	public void select() {
		selected = true;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage(GameManager.typeImage[type]), location.getX(), location.getY(), size.getX(), size.getY(), GameManager.getScreen());
		if (selected) {
			g.drawRect(location.getX(), location.getY(), size.getX(), size.getY());
			selected = false;
		}
	}
}
