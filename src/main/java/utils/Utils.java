package utils;

public class Utils {

	public Utils() {

	}

	public static int AnchoPantalla() {
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		return ancho;
	}

	public static int AltoPantalla() {
		int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		return alto;
	}

}
