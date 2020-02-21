package pers.darren.classloader;

public class Loader {

	public static void main(String[] args) {
		System.out.println("------------------------------------------");
		System.out.println(Parent.NAME);
		try {
			ClassLoader.getSystemClassLoader().loadClass("pers.darren.classloader.Sub");
			System.out.println("------------------------------------------");
			Class.forName("pers.darren.classloader.Sub");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------------");
	}
}