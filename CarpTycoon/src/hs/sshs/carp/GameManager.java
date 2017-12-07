package hs.sshs.carp;

import java.util.Queue;

public class GameManager {
	private GameScreen scr;
	private int frameRate;
	private long startTime;
	private Queue<CarpGuest> guest;
	private static GameManager mainManager;
	
	public GameManager(String title, int width, int height, int frameRate) {
		scr = new GameScreen(title, width, height);
		this.frameRate = frameRate;
		startTime = System.currentTimeMillis();
		mainManager = this;
	}
	
	public int getFrameRate() { return frameRate; }
	
	public void update() {
		scr.repaint();
	}
	
	public static GameManager getManager() {
		return mainManager;
	}
	
	public static GameScreen getScreen() {
		return mainManager.scr;
	}
}
