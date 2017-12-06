package hs.sshs.carp;

import java.awt.Graphics;

public abstract class GameObject implements Comparable<GameObject> {
	protected Point location;
	private final long index;
	private int depth;
	private static long objCount = 0;
	
	GameObject(int x, int y) {
		location = new Point(x, y);
		index = objCount++;
		depth = 0;
	}
	
	public void setX(int x) { location.setX(x); }
	public void setY(int y) { location.setY(y); }
	public void setDepth(int d) { depth = d; }
	public void move(Point p) {
		location.setX(p.getX());
		location.setY(p.getY());
	}
	public Point getLocation() { return location; }
	public abstract void draw(Graphics g);
	
	@Override
	public int compareTo(GameObject g) {
		if (depth < g.depth) return -1;
		if (depth > g.depth) return 1;
		if (index < g.index) return -1;
		if (index > g.index) return 1;
		return 0;
	}
}
