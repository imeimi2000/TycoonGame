package hs.sshs.carp;

import java.awt.Graphics;

public class Carp extends GameObject {
	public Carp(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(ImageManager.findImage("carp"), location.getX(), location.getY(), GameScreen.getScreen());
	}
}
