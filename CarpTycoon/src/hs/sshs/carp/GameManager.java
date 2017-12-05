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
	
	public void update() {
		
		
		scr.repaint();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameManager gm = new GameManager("Carp Tycoon", 640, 360, 60);
		try {
			gm.update();
			Thread.sleep(1000L / gm.getFrameRate());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
