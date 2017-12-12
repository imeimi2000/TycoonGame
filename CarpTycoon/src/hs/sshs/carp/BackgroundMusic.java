package hs.sshs.carp;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic implements Runnable {
	File soundFile;
	
	private BackgroundMusic(String fn) {
		soundFile = new File(fn);
	}
	
	public static void play(String fn) {
		BackgroundMusic bgm = new BackgroundMusic(fn);
		Thread tr = new Thread(bgm);
		tr.start();
	}
	
	public void run() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundFile));
			while (true) {
				clip.start();
				Thread.sleep(clip.getMicrosecondLength() / 1000L);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
