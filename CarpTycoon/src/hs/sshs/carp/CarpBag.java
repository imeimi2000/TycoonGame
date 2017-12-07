package hs.sshs.carp;

import java.awt.Graphics;

public class CarpBag extends GameObject {
	private static final Point size = new Point(78, 146);
	private static final Point textPosition = new Point(39, 73);
	private int count;
	
	public CarpBag(int x, int y) {
		super(x, y);
		count = 0;
	}
	
	public void addCount() { count++; }
	public void subCount() { count--; }
	public int getCount() { return count; }

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("paper_bag"), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
		g.drawString(count + "°³", location.getX() + textPosition.getX()
				, location.getY() + textPosition.getY());
	}

	@Override
	public boolean contains(Point p) {
		return location.getX() <= p.getX()
				&& p.getX() < location.getX() + size.getX()
				&& location.getY() <= p.getY()
				&& p.getY() < location.getY() + size.getY();
	}

}
