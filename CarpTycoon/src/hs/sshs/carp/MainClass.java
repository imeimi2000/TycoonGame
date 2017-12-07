package hs.sshs.carp;

import java.io.File;

public class MainClass {
	public static void main(String[] args) {
		try {
			ImageManager.init(new File("res/"));
			GameManager gm = new GameManager("Carp Tycoon", 1080, 720, 60);
			while (true) {
				gm.update();
				Thread.sleep(1000L / gm.getFrameRate());
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
