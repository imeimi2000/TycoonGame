package hs.sshs.carp;

import java.awt.Graphics;

public class MaterialSelector extends GameObject {
	private static final Point size = new Point(200, 106);
	
	private final int type;
	private boolean selected;
	
	MaterialSelector(int x, int y, int t) {
		super(x, y);
		type = t;
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

	@Override
	public boolean contains(Point p) {
		return location.getX() <= p.getX()
				&& p.getX() < location.getX() + size.getX()
				&& location.getY() <= p.getY()
				&& p.getY() < location.getY() + size.getY();
	}

}
