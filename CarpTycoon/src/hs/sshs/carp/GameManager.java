package hs.sshs.carp;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
	public static final String[] metarialName = { "ũ��", "��" };
	public static final String[] typeImage = { "cream", "red_bean" };
	
	public static final Point carpSize = new Point(200, 107);
	public static final Point bagSize = new Point(78, 146);
	
	private static final Point castPosition = new Point(400, 399);
	private static final Point selectorPosition = new Point(400, 100);
	private static final int bagY = 250;
	private static final Point guestPosition = new Point(100, 500);
	
	private static final int successScore = 10;
	private static final int wastePenalty = -1;
	private static final int guestReturnPenalty = -3;
	
	private static final int materialCount = 2;
	private static final int maxGuestCount = 3;
	private static final int carpCastCount = 9;
	
	private static final double newGuestTime = 2.5;
	
	private static GameManager mainManager;
	private static long updateTime;
	
	private GameScreen scr;
	private int frameRate;
	private long startTime;
	private long frameCount;
	private ArrayList<CarpGuest> guest;
	private Carp[] cast = new Carp[carpCastCount];
	private MaterialSelector[] matSel = new MaterialSelector[materialCount];
	private CarpBag[] bags = new CarpBag[materialCount];
	private int selectedMaterial;
	private Random rnd;
	private TextLabel scoreLabel;
	private TextLabel timeLabel;
	private int score;
	
	public static long currentTime() {
		return updateTime;
	}
	
	public static long currentFrame() {
		return mainManager.frameCount;
	}
	
	public GameManager(String title, int width, int height, int frameRate) throws IOException, FontFormatException {
		scr = new GameScreen(title, width, height);
		this.frameRate = frameRate;
		frameCount = 0;
		score = 0;
		startTime = System.currentTimeMillis();
		mainManager = this;
		guest = new ArrayList<CarpGuest>();
		selectedMaterial = -1;
		rnd = new Random(System.currentTimeMillis());
		for (int i = 0; i < cast.length; ++i) {
			cast[i] = new Carp(castPosition.getX() + carpSize.getX() * (i / 3)
					, castPosition.getY() + carpSize.getY() * (i % 3));
			scr.addObject(cast[i], 0);
		}
		for (int i = 0; i < materialCount; ++i) {
			matSel[i] = new MaterialSelector(selectorPosition.getX() + carpSize.getX() * i
					, selectorPosition.getY(), i);
			scr.addObject(matSel[i], 0);
			
			bags[i] = new CarpBag(selectorPosition.getX() + carpSize.getX() * i, bagY);
			scr.addObject(bags[i], 0);
		}
		score = 0;
		scoreLabel = new TextLabel(10, 70);
		scr.addObject(scoreLabel, 1);
		timeLabel = new TextLabel(10, 110);
		scr.addObject(timeLabel, 1);
	}
	
	public int getFrameRate() { return frameRate; }
	
	public void update() {
		updateTime = System.currentTimeMillis();
		
		if (frameCount % (int)(frameRate * newGuestTime) == 0) {
			if (guest.size() < maxGuestCount) {
				CarpGuest newGuest = new CarpGuest(guestPosition.getX(), 0, rnd.nextInt(materialCount));
				guest.add(newGuest);
				scr.addObject(newGuest, 0);
			}
			else {
				score += guestReturnPenalty;
			}
		}
		
		for (int i = 0; i < guest.size(); ++i) {
			guest.get(i).setY(guestPosition.getY() - 200 * i);
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
			
			for (int i = 0; i < bags.length; ++i) {
				if (bags[i].contains(clickPoint)) {
					if (bags[i].getCount() > 0
							&& guest.size() > 0
							&& guest.get(0).getType() == i) {
						scr.removeObject(guest.get(0));
						guest.remove(0);
						bags[i].subCount();
						score += successScore;
					}
					break;
				}
			}
			
			for (int i = 0; i < cast.length; ++i) {
				if (cast[i].contains(clickPoint)) {
					switch (cast[i].getType()) {
					case -3:
						cast[i].setType(-1);
						score += wastePenalty;
						break;
					case -2:
						cast[i].setType(-1);
						score += wastePenalty;
						break;
					case -1:
						if (selectedMaterial != -1) {
							cast[i].setType(selectedMaterial);
							selectedMaterial = -1;
						}
						break;
					default:
						bags[cast[i].getType()].addCount();
						cast[i].setType(-1);
						break;
					}
					break;
				}
			}
		}
		if (selectedMaterial != -1)
			matSel[selectedMaterial].select();
		scoreLabel.setText("Score : " + score);
		timeLabel.setText(String.format("Time : %.1fs", (updateTime - startTime) / 1000.0));
		
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
