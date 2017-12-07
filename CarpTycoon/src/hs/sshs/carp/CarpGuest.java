package hs.sshs.carp;

import java.awt.Graphics;

public class CarpGuest extends GameObject {
	private static final Point size = new Point(211, 200);
	
	private int type;
	
	CarpGuest(int x, int y, int t) {
		super(x, y);
		type = t;
	}

	public int getType() {
		return type;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("guest_1"), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
		g.drawString(GameManager.metarialName[type], location.getX() + size.getX() * 2 / 3
				, location.getY() + size.getY() * 7 / 24);
	}
	
	@Override
	public boolean contains(Point p) {
		return false;
	}
}
