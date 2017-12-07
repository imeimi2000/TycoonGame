package hs.sshs.carp;

import java.util.Queue;

public class GameManager {
	private static GameManager mainManager;
	private static long updateTime;
	
	private GameScreen scr;
	private int frameRate;
	private long startTime;
	private Queue<CarpGuest> guest;
	private Carp[] cast = new Carp[9];
	
	public static long currentTime() {
		return updateTime;
	}
	
	public GameManager(String title, int width, int height, int frameRate) {
		scr = new GameScreen(title, width, height);
		this.frameRate = frameRate;
		startTime = System.currentTimeMillis();
		mainManager = this;
		for (int i = 0; i < cast.length; ++i) {
			cast[i] = new Carp(200 + 200 * (i / 3), 720 - (107 * (i % 3 + 1)));
			scr.addObject(cast[i], 1);
		}
	}
	
	public int getFrameRate() { return frameRate; }
	
	public void update() {
		updateTime = System.currentTimeMillis();
		
		
		
		scr.repaint();
	}
	
	public static GameManager getManager() {
		return mainManager;
	}
	
	public static GameScreen getScreen() {
		return mainManager.scr;
	}
}
