package hs.sshs.carp;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
	public static final String[] metarialName = { "Å©¸²", "ÆÏ" };
	public static final String[] typeImage = { "cream", "red_bean" };
	
	private static GameManager mainManager;
	private static long updateTime;
	
	private GameScreen scr;
	private int frameRate;
	private long startTime;
	private long frameCount;
	private ArrayList<CarpGuest> guest;
	private Carp[] cast = new Carp[9];
	private MaterialSelector[] matSel = new MaterialSelector[2];
	private int selectedMaterial;
	private Random rnd;
	
	private final int metarialCount = 2;
	private final int maxGuestCount = 3;
	
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
		selectedMaterial = -1;
		rnd = new Random(System.currentTimeMillis());
		for (int i = 0; i < cast.length; ++i) {
			cast[i] = new Carp(400 + 200 * (i / 3), 720 - (107 * (i % 3 + 1)));
			scr.addObject(cast[i], 1);
		}
		for (int i = 0; i < matSel.length; ++i) {
			matSel[i] = new MaterialSelector(400 + i * 200, 100, i);
			scr.addObject(matSel[i], 0);
		}
	}
	
	public int getFrameRate() { return frameRate; }
	
	public void update() {
		updateTime = System.currentTimeMillis();
		
		if (guest.size() < maxGuestCount && frameCount % (frameRate * 3) == 0) {
			CarpGuest newGuest = new CarpGuest(100, 0, rnd.nextInt(metarialCount));
			guest.add(newGuest);
			scr.addObject(newGuest, 0);
		}
		
		for (int i = 0; i < guest.size(); ++i) {
			guest.get(i).setY(700 - 200 * (i + 1));
		}
		
		MouseInfo minfo = scr.getMouseInfo();
		if (minfo.mouseClicked()) {
			Point clickPoint = minfo.clickLocation();
			for (int i = 0; i < matSel.length; ++i) {
				if (matSel[i].contains(clickPoint)) {
					selectedMaterial = i;
					break;
				}
			}
			
			for (int i = 0; i < cast.length; ++i) {
				if (cast[i].contains(clickPoint)) {
					switch (cast[i].getType()) {
					case -3:
						cast[i].setType(-1);
						break;
					case -2:
						cast[i].setType(-1);
						break;
					case -1:
						if (selectedMaterial != -1) {
							cast[i].setType(selectedMaterial);
							selectedMaterial = -1;
						}
						break;
					default:
						if (guest.size() > 0
						 && guest.get(0).getType() == cast[i].getType()) {
							guest.remove(0);
						}
						cast[i].setType(-1);
						break;
					}
					break;
				}
			}
		}
		if (selectedMaterial != -1)
			matSel[selectedMaterial].select();
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
