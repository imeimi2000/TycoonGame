package hs.sshs.carp;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class GameScreen extends Frame implements MouseListener {
	private MouseInfo mouseInfo;
	private Point screenSize;
	private Set<GameObject> obj;
	private static Frame mainFrame;
	
	GameScreen(String title, int width, int height) {
		super(title);
		mouseInfo = new MouseInfo();
		screenSize = new Point(width, height);
		this.setSize(width, height);
		this.setVisible(true);
		obj = new TreeSet<GameObject>();
		mainFrame = this;
		this.addMouseListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static Frame getScreen() { return mainFrame; }
	public int getWidth() { return screenSize.getX(); }
	public int getHeight() { return screenSize.getY(); }
	public MouseInfo getMouseInfo() { return mouseInfo; }
	public void addObject(GameObject g) {
		obj.add(g);
	}
	
	public void removeObject(GameObject g) {
		obj.remove(g);
	}
	
	public void clearScreen() {
		obj.clear();
	}
	
	public void setDepth(GameObject g, int d) {
		obj.remove(g);
		g.setDepth(d);
		obj.add(g);
	}
	
	@Override
	public void paint(Graphics g) {
		Image img = createImage(screenSize.getX(), screenSize.getY());
		Graphics buf = img.getGraphics();
		
		for (GameObject i : obj) i.draw(buf);
		g.drawImage(img, 0, 0, this);
		mouseInfo.init();
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event) {}

	@Override
	public void mouseReleased(MouseEvent event) {
		System.out.println("Mouse Clicked (" + event.getX() +  "," + event.getY() + ")");
		mouseInfo.clickLocation(event.getX(), event.getY());
	}
}
