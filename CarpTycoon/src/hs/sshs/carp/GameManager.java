package hs.sshs.carp;

import java.io.File;

public class GameManager {
	private GameScreen scr;
	private int frameRate;
	private long startTime;
	public GameManager(String title, int width, int height, int frameRate) {
		scr = new GameScreen(title, width, height);
		this.frameRate = frameRate;
		startTime = System.currentTimeMillis();
	}
	
	public int getFrameRate() { return frameRate; }
	
	int idx;
	Carp c;
	TextLabel l;
	public void update() {
		if (idx == 0) {
			idx = 1;
			c = new Carp(540, 360);
			l = new TextLabel(40, 100);
			scr.addObject(c);
			scr.addObject(l);
		}
		if (scr.getMouseInfo().mouseClicked()) {
			c.move(scr.getMouseInfo().clickLocation());
			l.setText("Mouse Clicked " + scr.getMouseInfo().clickLocation());
		}
		scr.repaint();
	}
	
	public static void main(String[] args) {
		try {
			File path = new File("res/");
			for (File f : path.listFiles()) {
				String fn = f.getName();
				fn = fn.substring(0, fn.length() - 4);
				ImageManager.putImage(fn, f);
			}
			
			//ImageManager.findImage("ome");
			//ImageManager.putImage("omg", new File("omg.png"));
			
			GameManager gm = new GameManager("Carp Tycoon", 1080, 720, 60);
			while (true) {
				gm.update();
				Thread.sleep(1000L / gm.getFrameRate());
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}
}
