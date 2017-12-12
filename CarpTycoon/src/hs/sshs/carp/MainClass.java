package hs.sshs.carp;

public class MainClass {
	public static void main(String[] args) {
		try {
			GameManager gm = new GameManager("Carp Tycoon", 1280, 720, 60);
			BackgroundMusic.play("res/bgm.wav");
			
			while (true) {
				gm.update();
				Thread.sleep(1000L / gm.getFrameRate());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception Throwed, Exit Program");
			System.exit(0);
		}
	}
}
