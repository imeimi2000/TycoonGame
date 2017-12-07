package hs.sshs.carp;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class ImageNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1875353198585267759L;
	String fileName;
	ImageNotFoundException(String fname) {
		fileName = fname;
	}
	
	@Override
	public String getMessage() {
		return "Image \"" + fileName + "\" not found";
	}
}

public abstract class ImageManager {
	private static Map<String, Image> img = new HashMap<String, Image>();
	public static Image findImage(String name) throws ImageNotFoundException {
		Image ret = img.get(name);
		if (ret == null) throw new ImageNotFoundException(name);
		return ret;
	}
	public static void putImage(String name, String fn) throws IOException {
		img.put(name, new ImageIcon(fn).getImage());
		System.out.println("Read File \"" + fn + "\", " + name);
	}
	public static void putImage(String name, File f) throws IOException {
		img.put(name, ImageIO.read(f));
	}
	public static void init(File path) throws IOException {
		for (File f : path.listFiles()) {
			String fn = f.getName();
			putImage(fn.substring(0, fn.length() - 4), f);
			//putImage(fn.substring(0, fn.length() - 4), f.getAbsolutePath());
		}
	}
}
