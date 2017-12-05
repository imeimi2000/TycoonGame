package hs.sshs.carp;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public abstract class ImageManager {
	private static Map<String, Image> img = new HashMap<String, Image>();
	public static Image findImage(String name) {
		return img.get(name);
	}
	public static void putImage(String name, String fn) throws IOException {
		img.put(name, ImageIO.read(new File(fn)));
	}
}
