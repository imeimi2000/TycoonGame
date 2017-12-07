package hs.sshs.carp;

import java.awt.Graphics;

public class CarpGuest extends GameObject {
	private static final Point size = new Point(211, 200);
	
	CarpGuest(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("guest_1"), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
	}
	
	@Override
	public boolean contains(Point p) {
		return false;
	}
}
