package hs.sshs.carp;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

class ImageNotFoundException extends RuntimeException {
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
		img.put(name, ImageIO.read(new File(fn)));
	}
	public static void putImage(String name, File f) throws IOException {
		img.put(name, ImageIO.read(f));
	}
}
