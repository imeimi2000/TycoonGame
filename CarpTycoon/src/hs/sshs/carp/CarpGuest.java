package hs.sshs.carp;

import java.awt.Graphics;

public class CarpGuest extends GameObject {
	private static final Point size = new Point(211, 200);
	private static final Point textPosition = new Point(140, 58);
	
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
		g.drawString(GameManager.metarialName[type], location.getX() + textPosition.getX()
				, location.getY() + textPosition.getY());
	}
	
	@Override
	public boolean contains(Point p) {
		return false;
	}
}
