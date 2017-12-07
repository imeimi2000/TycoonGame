package hs.sshs.carp;

import java.util.ArrayList;

public class GameManager {
	private static GameManager mainManager;
	private static long updateTime;
	
	private GameScreen scr;
	private int frameRate;
	private long startTime;
	private long frameCount;
	private ArrayList<CarpGuest> guest;
	private final int maxGuestCount = 3;
	private Carp[] cast = new Carp[9];
	
	public static long currentTime() {
		return updateTime;
	}
	
	public GameManager(String title, int width, int height, int frameRate) {
		scr = new GameScreen(title, width, height);
		this.frameRate = frameRate;
		frameCount = 0;
		startTime = System.currentTimeMillis();
		mainManager = this;
		guest = new ArrayList<CarpGuest>();
		for (int i = 0; i < cast.length; ++i) {
			cast[i] = new Carp(400 + 200 * (i / 3), 720 - (107 * (i % 3 + 1)));
			scr.addObject(cast[i], 1);
		}
	}
	
	public int getFrameRate() { return frameRate; }
	
	public void update() {
		updateTime = System.currentTimeMillis();
		
		if (guest.size() < maxGuestCount && frameCount % (frameRate * 3) == 0) {
			CarpGuest newGuest = new CarpGuest(100, 0);
			guest.add(newGuest);
			scr.addObject(newGuest, 0);
		}
		
		for (int i = 0; i < guest.size(); ++i) {
			guest.get(i).setY(720 - 200 * (i + 1));
		}
		
		scr.repaint();
		frameCount++;
	}
	
	public static GameManager getManager() {
		return mainManager;
	}
	
	public static GameScreen getScreen() {
		return mainManager.scr;
	}
}
