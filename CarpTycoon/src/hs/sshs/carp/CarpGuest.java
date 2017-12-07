package hs.sshs.carp;

import java.awt.Graphics;

public class CarpGuest extends GameObject {
	CarpGuest(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		
	}
	
	@Override
	public boolean contains(Point p) {
		return false;
	}
}
