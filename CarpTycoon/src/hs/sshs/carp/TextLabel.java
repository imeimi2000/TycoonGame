package hs.sshs.carp;

import java.awt.Graphics;

public class TextLabel extends GameObject {
	private String text;
	
	TextLabel(int x, int y) {
		super(x, y);
		text = "";
	}
	
	TextLabel(int x, int y, String str) {
		super(x, y);
		setText(str);
	}
	
	public void setText(String str) {
		text = str;
	}

	@Override
	public void draw(Graphics g) {
		g.drawString(text, location.getX(), location.getY());
	}
}
