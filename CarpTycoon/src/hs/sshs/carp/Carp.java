package hs.sshs.carp;

import java.awt.Graphics;

public class Carp extends GameObject {
	private final Point size = new Point(0, 0);
	public Carp(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("carp"), location.getX(), location.getY(), GameManager.getScreen());
	}
	
	@Override
	public boolean contains(Point p) {
		return location.getX() <= p.getX()
				&& p.getX() < location.getX() + size.getX()
				&& location.getY() <= p.getY()
				&& p.getY() < location.getY() + size.getY();
	}
}
