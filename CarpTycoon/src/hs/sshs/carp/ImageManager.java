package hs.sshs.carp;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public abstract class ImageManager {
	private static Map<String, Image> img = new HashMap<String, Image>();
	public static Image findImage(String name) {
		return img.get(name);
	}
	public static void putImage(String name, String fn) {
		img.put(name, Toolkit.getDefaultToolkit().getImage(fn));
	}
}
