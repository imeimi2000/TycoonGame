package hs.sshs.carp;

import java.awt.Graphics;

public class Carp extends GameObject {
	public static final Point size = new Point(200, 106);
	private static final long completeTime = 5000;
	private static final long burnTime = 10000;
	private static final String[] typeImage = { "cream", "red_bean" };
	
	private long startTime;
	private int type;
	
	public Carp(int x, int y) {
		super(x, y);
		type = -1;
	}
	
	public void setType(int t) {
		type = t;
		if (type == -1) return;
		
		startTime = GameManager.currentTime();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("cast"), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
		if (type == -1) return;
		
		String fn;
		long t = GameManager.currentTime() - startTime;
		if (t < completeTime) fn = typeImage[type];
		else if (t < burnTime) fn = "carp";
		else fn = "burned_carp";
		g.drawImage(ImageManager.findImage(fn), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
	}
	
	@Override
	public boolean contains(Point p) {
		return location.getX() <= p.getX()
				&& p.getX() < location.getX() + size.getX()
				&& location.getY() <= p.getY()
				&& p.getY() < location.getY() + size.getY();
	}
}
