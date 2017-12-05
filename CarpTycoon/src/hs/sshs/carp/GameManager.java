package hs.sshs.carp;

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
	public void update() {
		if (idx == 0) {
			idx = 1;
			c = new Carp(540, 360);
			scr.addObject(c);
		}
		if (scr.getMouseInfo().mouseClicked()) {
			c.move(scr.getMouseInfo().clickLocation());
		}
		scr.repaint();
	}
	
	public static void main(String[] args) {
		try {
			ImageManager.putImage("carp", "res/carp.png");
			
			GameManager gm = new GameManager("Carp Tycoon", 1080, 720, 60);
			while (true) {
				gm.update();
				Thread.sleep(1000L / gm.getFrameRate());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
