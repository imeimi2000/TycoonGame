package hs.sshs.carp;

public abstract class ClickAble extends GameObject {
	protected Point size;
	
	ClickAble(int x, int y) {
		super(x, y);
	}
	
	public boolean contains(Point p) {
		return location.getX() <= p.getX()
				&& p.getX() < location.getX() + size.getX()
				&& location.getY() <= p.getY()
				&& p.getY() < location.getY() + size.getY();
	}
}
