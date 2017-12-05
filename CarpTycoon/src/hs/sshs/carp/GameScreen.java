package hs.sshs.carp;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
				dispose();
				System.exit(0);
			}
		});
	}
	
	public static Frame getScreen() { return mainFrame; }
	public int getWidth() { return screenSize.getX(); }
	public int getHeight() { return screenSize.getY(); }
	public void addObject(GameObject g) {
		obj.add(g);
	}
	
	public void removeObject(GameObject g) {
		obj.remove(g);
	}
	
	public void clearScreen() {
		obj.clear();
	}
	
	@Override
	public void paint(Graphics g) {
		for (GameObject i : obj) i.draw(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		mouseInfo.clickLocation(event.getX(), event.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
