package hs.sshs.carp;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
	private static final String[] images = {
		"burned_carp",
		"carp",
		"cast",
		"cream_carp",
		"cream",
		"green_carp",
		"green_cream",
		"guest_1",
		"kettle",
		"kimchi_carp",
		"kimchi",
		"paper_bag",
		"paste_carp",
		"paste",
		"recycle_bin",
		"red_bean",
		"red_carp",
	};
	
	public static Image findImage(String name) throws ImageNotFoundException {
		Image ret = img.get(name);
		if (ret == null) throw new ImageNotFoundException(name);
		return ret;
	}
	public static void putImage(String name, String fn) throws IOException {
		img.put(name, new ImageIcon(fn).getImage());
		System.err.println("Read File \"" + fn + "\", " + name);
	}
	public static void putImage(String name, File f) throws IOException {
		img.put(name, ImageIO.read(f));
		System.err.println("Read File \"" + f.getName() + "\", " + name);
	}
	public static void putImage(String name, InputStream f) throws IOException {
		img.put(name, ImageIO.read(f));
	}
	public static void putImage(String name, URL url) {
		System.err.println(url);
		img.put(name, new ImageIcon(url).getImage());
	}
	public static void init() throws IOException {
		for (String fn : images) {
			String str = "/" + fn + ".png";
			System.err.println(str);
			putImage(fn, MainClass.class.getResource(str));
		}
	}
}
