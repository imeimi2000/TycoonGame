package hs.sshs.carp;

import java.awt.Graphics;

public class Carp extends GameObject {
	public static final Point size = new Point(200, 106);
	private static final double completeTime = 5.0;
	private static final double burnTime = 10.0;
	
	private final long carpCompleteTime;
	private final long carpBurnTime;
	private long startTime;
	private int type;
	
	public Carp(int x, int y) {
		super(x, y);
		carpCompleteTime = (int)(GameManager.getManager().getFrameRate() * completeTime);
		carpBurnTime = (int)(GameManager.getManager().getFrameRate() * burnTime);
		type = -1;
	}
	
	public void setType(int t) {
		type = t;
		if (type == -1) return;
		
		startTime = GameManager.currentTime();
	}
	
	public int getType() {
		if (type == -1) return -1;
		long t = GameManager.currentTime() - startTime;
		if (t < carpCompleteTime) return -2;
		if (t < carpBurnTime) return type;
		return -3;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("cast"), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
		if (type == -1) return;
		
		String fn;
		long t = GameManager.currentTime() - startTime;
		if (t < carpCompleteTime) fn = GameManager.typeImage[type];
		else if (t < carpBurnTime) fn = "carp";
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
