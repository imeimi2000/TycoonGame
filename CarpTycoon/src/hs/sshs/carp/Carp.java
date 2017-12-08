package hs.sshs.carp;

import java.awt.Graphics;

public class Carp extends ClickAble {
	public static final int EMPTY = -1, INCOMPLETE = -2, BURNED = -3;
	
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
		size = GameManager.carpSize;
	}
	
	public void setType(int t) {
		type = t;
		if (type == EMPTY) return;
		
		startTime = GameManager.currentFrame();
	}
	
	public int getType() {
		if (type == EMPTY) return EMPTY;
		long t = GameManager.currentFrame() - startTime;
		if (t < carpCompleteTime) return INCOMPLETE;
		if (t < carpBurnTime) return type;
		return BURNED;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.findImage("cast"), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
		if (type == EMPTY) return;
		
		String fn;
		long t = GameManager.currentFrame() - startTime;
		if (t < carpCompleteTime) fn = GameManager.typeImage[type];
		else if (t < carpBurnTime) fn = "carp";
		else fn = "burned_carp";
		g.drawImage(ImageManager.findImage(fn), location.getX(), location.getY()
				, size.getX(), size.getY(), GameManager.getScreen());
	}
}
